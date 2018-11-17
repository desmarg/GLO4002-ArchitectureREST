package trading.services;

import trading.application.JerseyClient;
import trading.persistence.AccountRepositoryInMemory;
import trading.persistence.TransactionRepositoryInMemory;

public class Services {
    private final TransactionService transactionService;
    private final AccountService accountService;

    public Services() {
        JerseyClient jerseyClient = new JerseyClient();
        this.accountService = new AccountService(new AccountRepositoryInMemory());
        StockService stockService = new StockService(jerseyClient);
        MarketService marketService = new MarketService(jerseyClient);
        ReportService reportService = new ReportService(stockService);
        this.transactionService = new TransactionService(new TransactionRepositoryInMemory(), stockService, marketService, this.accountService, reportService);
    }

    public TransactionService getTransactionService() {
        return this.transactionService;
    }

    public AccountService getAccountService() {
        return this.accountService;
    }

}
