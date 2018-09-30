package persistence;

import domain.account.Account;
import domain.account.AccountNumber;
import exception.AccountNotFoundException;

public interface AccountRepository {

    void add(Account account);

    Account findByAccountNumber(AccountNumber accountNumber) throws AccountNotFoundException;

    boolean checkIfAccountExists(Long investorId);

    long nextCounterValue();
}
