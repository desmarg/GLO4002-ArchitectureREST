package trading.services;

import trading.application.JerseyClient;
import trading.persistence.*;

public class Services {
    private final TransactionService transactionService;
    private final AccountService accountService;

    public Services() {
        JerseyClient jerseyClient = new JerseyClient();
        StockAPIRepository stockAPIRepository = new StockAPIRepository(jerseyClient);
        MarketAPIRepository marketAPIRepository = new MarketAPIRepository(jerseyClient);
        AccountRepositoryHibernate accountRepositoryHibernate = new AccountRepositoryHibernate();

        this.accountService = new AccountService(accountRepositoryHibernate);
        this.transactionService = new TransactionService(new TransactionRepositoryHibernate(),
                stockAPIRepository, marketAPIRepository, accountRepositoryHibernate, new BasicForexRates());
    }

    public TransactionService getTransactionService() {
        return this.transactionService;
    }

    public AccountService getAccountService() {
        return this.accountService;
    }

}
