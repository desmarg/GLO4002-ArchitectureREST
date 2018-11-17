package trading.services;

import trading.domain.Credits.Credits;
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

    public Portfolio getPortfolio(Credits initialCredits, DateTime dateTime, List<TransactionBuy> transactionBuyHistory, List<TransactionSell> transactionSellHistory) {

        Credits portfolioValue = new Credits();
        Map<Stock, Long> quantityByStock = new HashMap<>();
        Credits creditsInAccount = initialCredits;

        for (TransactionBuy transaction : transactionBuyHistory) {
            creditsInAccount.subtract(transaction.getValueWithFees());
            Stock stock = transaction.getStock();
            Long quantity = quantityByStock.get(stock);
            if (quantity == null) {
                quantityByStock.put(stock, transaction.getQuantity());
            } else {
                quantityByStock.put(stock, transaction.getQuantity() + quantity);
            }
        }
        for (TransactionSell transaction : transactionSellHistory) {
            creditsInAccount.subtract(transaction.getFees());
            creditsInAccount.add(transaction.getValue());
            Stock stock = transaction.getStock();
            Long quantity = quantityByStock.get(stock);
            quantityByStock.put(stock, quantity - transaction.getQuantity());
        }
        for (Map.Entry<Stock, Long> entry : quantityByStock.entrySet()) {
            Credits actualPrice = this.stockService.retrieveStockPrice(entry.getKey(), dateTime);
            Long quantity = entry.getValue();
            actualPrice.multiply(quantity);
            portfolioValue.add(actualPrice);
        }
        return new Portfolio(portfolioValue, creditsInAccount);
    }

}
