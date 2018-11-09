package trading.services;

import application.market.MarketService;
import trading.persistence.AccountRepositoryInMemory;
import trading.persistence.TransactionRepositoryInMemory;

public class Services {
    private TransactionService transactionService;
    private AccountService accountService;

    public Services() {
        this.transactionService = new TransactionService(new TransactionRepositoryInMemory(), new StockService(), new MarketService());
        this.accountService = new AccountService(new AccountRepositoryInMemory());
    }

    public TransactionService getTransactionService() {
        return this.transactionService;
    }

    public AccountService getAccountService() {
        return this.accountService;
    }
}
