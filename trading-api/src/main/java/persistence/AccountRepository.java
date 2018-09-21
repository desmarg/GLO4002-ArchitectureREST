package persistence;

import models.Account;

import java.util.HashMap;

public class AccountRepository implements Repository<Long, Account> {
    private static AccountRepository ourInstance = new AccountRepository();
    public static AccountRepository getInstance() {
        return ourInstance;
    }

    private HashMap<Long, Account> accountMap;

    private AccountRepository() {
        this.accountMap = new HashMap<>();
    }

    public void save(Account account) {
        accountMap.put(account.getAccountNumber(), account);
    }

    public void delete(Long key) {
        accountMap.remove(key);
    }

    public Account fetchOne(Long key) {
        return accountMap.get(key);
    }
}
