package trading.domain.report;

import trading.domain.*;
import trading.domain.datetime.DateTime;
import trading.domain.transaction.Transaction;
import trading.domain.transaction.TransactionBuy;
import trading.domain.transaction.TransactionSell;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Report {

    public final DateTime date;
    public final List<Transaction> transactions;
    public HashMap<Currency, Credits> credits;
    public Credits portfolioValue;

    public Report(
            DateTime date,
            List<Transaction> transactionList,
            HashMap<Currency, Credits> initialCredits,
            List<TransactionBuy> transactionBuyHistory,
            List<TransactionSell> transactionSellHistory,
            ForeignExchangeRepository forexRepo,
            StockRepository stockRepository
    ) {
        this.date = date;
        this.transactions = transactionList;
        this.buildPortfolio(initialCredits, transactionBuyHistory, transactionSellHistory, forexRepo, stockRepository);
    }

    private void buildPortfolio(
            HashMap<Currency, Credits> initialCredits,
            List<TransactionBuy> transactionBuyHistory,
            List<TransactionSell> transactionSellHistory,
            ForeignExchangeRepository forexRepo,
            StockRepository stockRepository
    ) {
        Credits portfolioValue = Credits.getZeroCredits(Currency.CAD);
        Map<Stock, Long> quantityByStock = new HashMap<>();
        HashMap<Currency, Credits> creditsInAccount = initialCredits;

        calculateCreditsInAccount(transactionBuyHistory, transactionSellHistory, quantityByStock, creditsInAccount);
        this.portfolioValue = calculatePortfolio(date, forexRepo, portfolioValue, quantityByStock, stockRepository);
        this.credits = creditsInAccount;
    }

    private Credits calculatePortfolio(DateTime dateTime, ForeignExchangeRepository forexRepo, Credits portfolioValue, Map<Stock, Long> quantityByStock, StockRepository stockRepository) {
        for (Stock stock : quantityByStock.keySet()) {
            Long stockQuantity = quantityByStock.get(stock);
            Credits stockPrice = stockRepository.retrieveStockPrice(stock, dateTime, true);
            Credits stockValue = stockPrice.multiply(Credits.fromLong(stockQuantity, stockPrice.getCurrency()));
            Credits convertedStockValue = forexRepo.convertToCAD(stockValue);
            portfolioValue = portfolioValue.add(convertedStockValue);
        }
        return portfolioValue;
    }

    private void calculateCreditsInAccount(List<TransactionBuy> transactionBuyHistory, List<TransactionSell> transactionSellHistory, Map<Stock, Long> quantityByStock, HashMap<Currency, Credits> creditsInAccount) {
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
    }
}
