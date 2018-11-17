package trading.services;

import trading.application.JerseyClient;
import trading.persistence.AccountRepositoryInMemory;
import trading.persistence.TransactionRepositoryInMemory;

public class Services {
    private final TransactionService transactionService;
    private final AccountService accountService;
    private final StockService stockService;
    private final MarketService marketService;
    private final ReportService reportService;
    private final JerseyClient jerseyClient;

    public Services() {
        this.jerseyClient = new JerseyClient();
        this.accountService = new AccountService(new AccountRepositoryInMemory());
        this.stockService = new StockService(this.jerseyClient);
        this.marketService = new MarketService(this.jerseyClient);
        this.reportService = new ReportService(this.stockService);
        this.transactionService = new TransactionService(new TransactionRepositoryInMemory(), this.stockService, this.marketService, this.accountService, this.reportService);
    }

    public TransactionService getTransactionService() {
        return this.transactionService;
    }

    public AccountService getAccountService() {
        return this.accountService;
    }

}
