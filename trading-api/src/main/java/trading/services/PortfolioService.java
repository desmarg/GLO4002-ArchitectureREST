package trading.services;

import trading.domain.Account.Account;
import trading.domain.Credits.Credits;
import trading.domain.DateTime.DateTime;
import trading.domain.Report.Portfolio;
import trading.domain.Report.StockPortfolio;
import trading.domain.Stock;
import trading.domain.transaction.TransactionBuy;
import trading.domain.transaction.TransactionNumber;
import trading.domain.transaction.TransactionSell;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PortfolioService {

    private final StockService stockService;

    public PortfolioService(StockService stockService) {
        this.stockService = stockService;
    }

    public Portfolio getPortfolioValue(Account account, DateTime dateTime, List<TransactionBuy> transactionBuyHistory, List<TransactionSell> transactionSellHistory) {

        Credits accountValue = account.getInitialCredits();
        Credits portfolioValue = new Credits();
        Map<TransactionNumber, StockPortfolio> stockPortfoliosMap = new HashMap<>();

        for (TransactionBuy transaction : transactionBuyHistory) {
            Stock stock = transaction.getStock();
            Credits actualPrice = this.stockService.retrieveStockPrice(stock, dateTime);
            Long quantity = transaction.getQuantity();
            StockPortfolio stockPortfolio = new StockPortfolio(actualPrice, quantity);
            accountValue.subtract(transaction.getValueWithFees());
            stockPortfoliosMap.put(transaction.getTransactionNumber(), stockPortfolio);
        }
        for (TransactionSell transaction : transactionSellHistory) {
            Long quantity = transaction.getQuantity();
            accountValue.subtract(transaction.getFees());
            accountValue.add(transaction.getValue());
            StockPortfolio stockPortfolio = stockPortfoliosMap.get(transaction.getReferredTransactionNumber());
            stockPortfolio.subtract(quantity);
            stockPortfoliosMap.put(transaction.getTransactionNumber(), stockPortfolio);
        }
        for (StockPortfolio stockPortfolio : stockPortfoliosMap.values()) {
            portfolioValue.add(stockPortfolio.getValue());
        }
        return new Portfolio(portfolioValue, accountValue);
    }

}
