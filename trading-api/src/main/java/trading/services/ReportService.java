package trading.services;

import trading.domain.Credits;
import trading.domain.Currency;
import trading.domain.ForeignExchangeRepository;
import trading.domain.Stock;
import trading.domain.datetime.DateTime;
import trading.domain.report.Portfolio;
import trading.domain.transaction.TransactionBuy;
import trading.domain.transaction.TransactionSell;
import trading.persistence.StockAPIRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {

    private final StockAPIRepository stockAPIRepository;

    public ReportService(StockAPIRepository stockAPIRepository) {
        this.stockAPIRepository = stockAPIRepository;
    }

    public Portfolio getPortfolio(HashMap<Currency, Credits> initialCredits, DateTime dateTime,
                                  List<TransactionBuy> transactionBuyHistory,
                                  List<TransactionSell> transactionSellHistory, ForeignExchangeRepository forexRepo) {

        Credits portfolioValue = Credits.getZeroCredits(Currency.CAD);
        Map<Stock, Long> quantityByStock = new HashMap<>();
        HashMap<Currency, Credits> creditsInAccount = initialCredits;

        for (TransactionBuy transaction : transactionBuyHistory) {
            Credits transactionValueWithFees = transaction.getValueWithFees();
            Currency transactionCurrency = transactionValueWithFees.getCurrency();
            creditsInAccount.merge(transactionCurrency, transactionValueWithFees, Credits::subtract);
            Stock stock = transaction.getStock();
            quantityByStock.merge(stock, transaction.getQuantity(), (a, b) -> b + a);
        }
        for (TransactionSell transaction : transactionSellHistory) {
            Credits transactionFees = transaction.getFees();
            Credits transactionValue = transaction.getValue();
            Currency transactionCurrency = transactionValue.getCurrency();
            creditsInAccount.merge(transactionCurrency, transactionFees, Credits::subtract);
            creditsInAccount.merge(transactionCurrency, transactionValue, Credits::add);
            Stock stock = transaction.getStock();
            quantityByStock.merge(stock, transaction.getQuantity(), (a, b) -> a - b);
        }
        for (Stock stock : quantityByStock.keySet()) {
            Long stockQuantity = quantityByStock.get(stock);
            Credits stockPrice = this.stockAPIRepository.retrieveStockPrice(stock, dateTime, true);
            Credits stockValue = stockPrice.multiply(Credits.fromLong(stockQuantity, stockPrice.getCurrency()));
            Credits convertedStockValue = forexRepo.convertToCAD(stockValue);
            portfolioValue = portfolioValue.add(convertedStockValue);
        }
        return new Portfolio(portfolioValue, creditsInAccount);
    }
}
