package trading.services;

import trading.domain.Credits.Credits;
import trading.domain.DateTime.DateTime;
import trading.domain.Report.Portfolio;
import trading.domain.Report.StockPortfolio;
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

    public Portfolio getPortfolio(Credits creditsInAccount, DateTime dateTime, List<TransactionBuy> transactionBuyHistory, List<TransactionSell> transactionSellHistory) {

        Credits portfolioValue = new Credits();
        Map<TransactionNumber, StockPortfolio> stockPortfoliosMap = new HashMap<>();

        for (TransactionBuy transaction : transactionBuyHistory) {
            Credits actualPrice = this.stockService.retrieveStockPrice(transaction.getStock(), dateTime);
            StockPortfolio stockPortfolio = new StockPortfolio(actualPrice, transaction.getQuantity());
            creditsInAccount.subtract(transaction.getValueWithFees());
            stockPortfoliosMap.put(transaction.getTransactionNumber(), stockPortfolio);
        }
        for (TransactionSell transaction : transactionSellHistory) {
            creditsInAccount.subtract(transaction.getFees());
            creditsInAccount.add(transaction.getValue());
            StockPortfolio stockPortfolio = stockPortfoliosMap.get(transaction.getReferredTransactionNumber());
            stockPortfolio.substract(transaction.getQuantity());
            stockPortfoliosMap.put(transaction.getTransactionNumber(), stockPortfolio);
        }
        for (StockPortfolio stockPortfolio : stockPortfoliosMap.values()) {
            portfolioValue.add(stockPortfolio.getValue());
        }
        return new Portfolio(portfolioValue, creditsInAccount);
    }

}
