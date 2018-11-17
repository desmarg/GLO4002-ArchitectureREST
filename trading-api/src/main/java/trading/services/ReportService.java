package trading.services;

import trading.domain.Credits;
import trading.domain.DateTime.DateTime;
import trading.domain.Report.Portfolio;
import trading.domain.Stock;
import trading.domain.transaction.TransactionBuy;
import trading.domain.transaction.TransactionSell;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {

    private final StockService stockService;

    public ReportService(StockService stockService) {
        this.stockService = stockService;
    }

    public Portfolio getPortfolio(
            Credits initialCredits,
            DateTime dateTime,
            List<TransactionBuy> transactionBuyHistory,
            List<TransactionSell> transactionSellHistory
    ) {

        Credits portfolioValue = Credits.ZERO;
        Map<Stock, Long> quantityByStock = new HashMap<>();
        Credits creditsInAccount = initialCredits;

        for (TransactionBuy transaction : transactionBuyHistory) {
            creditsInAccount = creditsInAccount.subtract(transaction.getValueWithFees());
            Stock stock = transaction.getStock();
            Long quantity = quantityByStock.get(stock);
            if (quantity == null) {
                quantityByStock.put(stock, transaction.getQuantity());
            } else {
                quantityByStock.put(stock, transaction.getQuantity() + quantity);
            }
        }
        for (TransactionSell transaction : transactionSellHistory) {
            creditsInAccount = creditsInAccount.subtract(transaction.getFees()).add(transaction.getValue());
            Stock stock = transaction.getStock();
            Long quantity = quantityByStock.get(stock);
            quantityByStock.put(stock, quantity - transaction.getQuantity());
        }
        for (Map.Entry<Stock, Long> entry : quantityByStock.entrySet()) {
            portfolioValue = portfolioValue.add(
                    this.stockService.retrieveStockPrice(entry.getKey(), dateTime)
                            .multiply(Credits.fromLong(entry.getValue())));
        }
        return new Portfolio(portfolioValue, creditsInAccount);
    }

}
