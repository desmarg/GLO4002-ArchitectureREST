package trading.persistence;

import trading.domain.Account;
import trading.domain.AccountNumber;

public interface AccountRepository {
    Account save(Account account);

    Account findByAccountNumber(AccountNumber accountNumber);

    boolean accountAlreadyExists(Long investorId);
}
