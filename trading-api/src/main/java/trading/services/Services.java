package trading.services;

import trading.persistence.AccountRepositoryInMemory;
import trading.persistence.TransactionRepositoryInMemory;

public class Services {
    private final TransactionService transactionService;
    private final AccountService accountService;

    public Services() {
        this.accountService = new AccountService(new AccountRepositoryInMemory());
        this.transactionService = new TransactionService(new TransactionRepositoryInMemory(), new StockService(), new MarketService(), this.accountService);
    }

    public TransactionService getTransactionService() {
        return this.transactionService;
    }

    public AccountService getAccountService() {
        return this.accountService;
    }
}
