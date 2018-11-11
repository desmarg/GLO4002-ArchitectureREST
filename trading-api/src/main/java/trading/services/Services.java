package trading.services;

import trading.application.JerseyClient;
import trading.persistence.AccountRepositoryInMemory;
import trading.persistence.TransactionRepositoryInMemory;

public class Services {
    private final TransactionService transactionService;
    private final AccountService accountService;
    private final JerseyClient jerseyClient;

    public Services() {
        this.jerseyClient = new JerseyClient();
        this.accountService = new AccountService(new AccountRepositoryInMemory());
        this.transactionService = new TransactionService(new TransactionRepositoryInMemory(), new StockService(this.jerseyClient), new MarketService(), this.accountService);
    }

    public TransactionService getTransactionService() {
        return this.transactionService;
    }

    public AccountService getAccountService() {
        return this.accountService;
    }
}
