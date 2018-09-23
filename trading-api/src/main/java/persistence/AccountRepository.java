package persistence;

import domain.Account;

public interface AccountRepository {
    long add(Account account);
    Account findByAccountNumber(long accountNumber);
}
