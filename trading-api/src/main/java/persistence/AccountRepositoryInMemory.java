package persistence;

import domain.Account;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class AccountRepositoryInMemory implements AccountRepository {

    private Map<Long, Account> accountMap = new HashMap<>();

    public long add(Account account) {
        accountMap.put(account.getInvestorId(), account);
        return account.getAccountNumber();
    }

    public Account findByAccountNumber(long accountNumber) throws AccountNotFoundByAccountNumberException{
        for (Account account : accountMap.values()) {
            if (accountNumber == account.getAccountNumber()) {
                return account;
            }
        }
        throw new AccountNotFoundByAccountNumberException("Account not found by account number", accountNumber);
    }

    public boolean checkIfAccountExists(long investorId) {
        return accountMap.containsKey(investorId);
    }
}