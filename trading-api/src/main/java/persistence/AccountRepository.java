package persistence;

import domain.Account;
import domain.AccountNumber;
import exception.AccountNotFoundException;

public interface AccountRepository {

    void add(Account account);

    Account findByAccountNumber(AccountNumber accountNumber) throws AccountNotFoundException;

    boolean checkIfAccountExists(Long investorId);

    long nextCounterValue();
}
