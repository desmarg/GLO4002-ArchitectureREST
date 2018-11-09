package trading.services;

import application.market.MarketService;
import trading.persistence.AccountRepositoryInMemory;
import trading.persistence.TransactionRepositoryInMemory;

public class Services {
    private TransactionService transactionService;
    private AccountService accountService;
    private StockService stockService;
    private MarketService marketService;

    public Services() {
        this.transactionService = new TransactionService(new TransactionRepositoryInMemory());
        this.accountService = new AccountService(new AccountRepositoryInMemory());
        this.stockService = new StockService();
        this.marketService = new MarketService();
    }

    public TransactionService getTransactionService() {
        return this.transactionService;
    }

    public AccountService getAccountService() {
        return this.accountService;
    }

    public StockService getStockService() {
        return this.stockService;
    }

    public MarketService getMarketService() {
        return this.marketService;
    }
}
