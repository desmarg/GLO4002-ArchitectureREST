package trading.services;

import trading.persistence.AccountRepositoryInMemory;

public class Services {
	private TransactionService transactionService;
	private AccountService accountService;
	public Services() {
		this.transactionService = new TransactionService();
		this.accountService = new AccountService(new AccountRepositoryInMemory());
	}


	public TransactionService getTransactionService() {
		return transactionService;
	}


	public AccountService getAccountService() {
		return accountService;
	}
}
