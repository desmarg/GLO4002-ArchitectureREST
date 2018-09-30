package persistence;

import domain.account.Account;
import domain.account.AccountNumber;
import exception.AccountNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class AccountRepositoryInMemory implements AccountRepository {

    private Map<Long, AccountNumber> investorIdByAccountNumber = new HashMap<>();
    private Map<AccountNumber, Account> accountMap = new HashMap<>();
    private static AccountNumber ACCOUNT_NUMBER_COUNTER = new AccountNumber(0L);

    public void add(Account account) {
        ACCOUNT_NUMBER_COUNTER.incrementId();
        this.investorIdByAccountNumber.put(account.getInvestorId(), account.getAccountNumber());
        this.accountMap.put(account.getAccountNumber(), account);
    }

    public Account findByAccountNumber(AccountNumber accountNumber) throws AccountNotFoundException {
        Account account = this.accountMap.get(accountNumber);
        if (account != null) {
            return account;
        }
        throw new AccountNotFoundException(accountNumber);
    }

    public boolean checkIfAccountExists(Long investorId) {
        return this.investorIdByAccountNumber.containsKey(investorId);
    }

    public long nextCounterValue() {
        return ACCOUNT_NUMBER_COUNTER.getId() + 1;
    }
}
