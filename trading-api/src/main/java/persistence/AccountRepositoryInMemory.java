package persistence;

import domain.Account;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class AccountRepositoryInMemory implements AccountRepository {

    private Map<Long, Account> accountMap = new HashMap<>();

    public long add(Account account) {
        if (accountMap.containsKey(account.getInvestorId())) {
            throw new AccountAlreadyExistsException(MessageFormat.format("Cannot create account, an account with investorId {0} already exists.",account.getInvestorId()));
        }
        accountMap.put(account.getInvestorId(), account);
        return account.getAccountNumber();
    }

    public Account findByAccountNumber(long accountNumber) {
        for (Account account : accountMap.values()) {
            if (accountNumber == account.getAccountNumber()) {
                return account;
            }
        }
        throw new AccountNotFoundByAccountNumberException("Account not found by account number");
    }
}