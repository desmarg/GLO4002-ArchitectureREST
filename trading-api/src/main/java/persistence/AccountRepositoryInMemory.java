package persistence;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import domain.Account;

public class AccountRepositoryInMemory implements AccountRepository {

    private Map<Long, Account> accountMap = new HashMap<>();

    public void add(Account account) {
        if (accountMap.containsKey(account.getInvestorId())){
            throw new AccountAlreadyExistsException(MessageFormat.format("Cannot create account, an account with investorId {0} already exists.",account.getInvestorId()));
        }
        accountMap.put(account.getInvestorId(), account);
    }

    public Account findByAccountNumber(long accountNumber){
        for (Account account : accountMap.values()) {
            if (accountNumber == account.getAccountNumber()) {
                return account;
            }
        }
        throw new AccountNotFoundException("Account not found");
    }
}