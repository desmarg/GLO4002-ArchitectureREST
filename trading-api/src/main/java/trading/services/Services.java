package trading.services;

import trading.application.JerseyClient;
import trading.persistence.AccountRepositoryInMemory;
import trading.persistence.BasicForexRates;
import trading.persistence.MarketAPIRepository;
import trading.persistence.TransactionRepositoryInMemory;

public class Services {
    private final TransactionService transactionService;
    private final AccountService accountService;

    public Services() {
        JerseyClient jerseyClient = new JerseyClient();
        StockAPIRepository stockAPIRepository = new StockAPIRepository(jerseyClient);
        MarketAPIRepository marketAPIRepository = new MarketAPIRepository(jerseyClient);

        this.accountService = new AccountService(new AccountRepositoryInMemory());
        ReportService reportService = new ReportService(stockAPIRepository);
        this.transactionService = new TransactionService(new TransactionRepositoryInMemory(),
                stockAPIRepository, marketAPIRepository, this.accountService, reportService, new BasicForexRates());
    }

    public TransactionService getTransactionService() {
        return this.transactionService;
    }

    public AccountService getAccountService() {
        return this.accountService;
    }

}
