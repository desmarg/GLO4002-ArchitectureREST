package trading.persistence;

import trading.domain.Account;
import trading.domain.AccountNumber;
import trading.exception.AccountNotFoundException;

public interface AccountRepository {

    void add(Account account);

    Account findByAccountNumber(AccountNumber accountNumber) throws AccountNotFoundException;

    boolean accountAlreadyExists(Long investorId);

    long nextCounterValue();
}
