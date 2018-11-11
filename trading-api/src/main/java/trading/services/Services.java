package trading.services;

import trading.persistence.AccountRepositoryInMemory;
import trading.persistence.TransactionRepositoryInMemory;
import trading.persistence.WalletRepositoryInMemory;

public class Services {
    private final TransactionService transactionService;
    private final AccountService accountService;
    private final WalletService walletService;

    public Services() {
        this.walletService = new WalletService(new WalletRepositoryInMemory());
        this.accountService = new AccountService(new AccountRepositoryInMemory(), this.walletService);
        this.transactionService = new TransactionService(new TransactionRepositoryInMemory(), this.walletService, new StockService(), new MarketService(), this.accountService);
    }

    public TransactionService getTransactionService() {
        return this.transactionService;
    }

    public AccountService getAccountService() {
        return this.accountService;
    }
}
