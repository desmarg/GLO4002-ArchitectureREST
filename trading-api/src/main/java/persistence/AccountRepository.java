package persistence;

import domain.Account;

public interface AccountRepository {
    void add(Account account);
    Account findByAccountNumber(long investorId);
}
