package trading.application.services;

import trading.application.JerseyClient;
import trading.persistence.*;

public class AppContext {
    private final TransactionApplicationService transactionApplicationService;
    private final AccountApplicationService accountApplicationService;

    public AppContext() {
        JerseyClient jerseyClient = new JerseyClient();
        StockAPIRepository stockAPIRepository = new StockAPIRepository(jerseyClient);
        MarketAPIRepository marketAPIRepository = new MarketAPIRepository(jerseyClient);
        AccountRepositoryHibernate accountRepositoryHibernate = new AccountRepositoryHibernate();

        this.accountApplicationService = new AccountApplicationService(accountRepositoryHibernate);
        this.transactionApplicationService = new TransactionApplicationService(new TransactionRepositoryHibernate(),
                stockAPIRepository, marketAPIRepository, accountRepositoryHibernate, new BasicForexRates());
    }

    public TransactionApplicationService getTransactionApplicationService() {
        return this.transactionApplicationService;
    }

    public AccountApplicationService getAccountApplicationService() {
        return this.accountApplicationService;
    }

}
