package trading.services;

import trading.domain.Credits.Credits;
import trading.domain.DateTime.DateTime;
import trading.domain.Report.Portfolio;
import trading.domain.Report.StockPortfolio;
import trading.domain.Stock;
import trading.domain.transaction.Transaction;
import trading.domain.transaction.TransactionBuy;
import trading.domain.transaction.TransactionNumber;
import trading.domain.transaction.TransactionSell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PortfolioService {

    private final StockService stockService;

    public PortfolioService(StockService stockService) {
        this.stockService = stockService;
    }

    public Portfolio fromTransactionHistory(List<Transaction> transactionHistory, DateTime date, StockService stockService) {
        Map<TransactionNumber, StockPortfolio> stockPortfoliosMap = this.createStockPortfoliosMapFromBuyTransactions(transactionHistory, date);
        this.deductSellTransactionsFromStockPortfolios(transactionHistory, stockPortfoliosMap);
        List<StockPortfolio> stockPortfolios = new ArrayList<StockPortfolio>(stockPortfoliosMap.values());
        return new Portfolio(date, stockPortfolios);
    }

    private Map<TransactionNumber, StockPortfolio> createStockPortfoliosMapFromBuyTransactions(List<Transaction> transactionHistory, DateTime date) {
        Map<TransactionNumber, StockPortfolio> stockPortfoliosMap = new HashMap<>();
        for (Transaction transaction : transactionHistory) {
            if (transaction instanceof TransactionBuy) {
                Stock stock = transaction.getStock();
                Credits price = this.stockService.retrieveStockPrice(stock, date);
                Long amount = transaction.getQuantity();
                StockPortfolio stockPortfolio = new StockPortfolio(stock, price, amount);
                stockPortfoliosMap.put(transaction.getTransactionNumber(), stockPortfolio);
            }
        }
        return stockPortfoliosMap;
    }

    private void deductSellTransactionsFromStockPortfolios(List<Transaction> transactionHistory, Map<TransactionNumber, StockPortfolio> stockPortfoliosMap) {
        for (Transaction transaction : transactionHistory) {
            if (transaction instanceof TransactionSell) {
                TransactionNumber referredTransactionNumber = ((TransactionSell) transaction).getReferredTransactionNumber();
                StockPortfolio stockPortfolio = stockPortfoliosMap.get(referredTransactionNumber);
                stockPortfolio.deduct(transaction.getQuantity());
                stockPortfoliosMap.put(referredTransactionNumber, stockPortfolio);
            }
        }
    }
}
