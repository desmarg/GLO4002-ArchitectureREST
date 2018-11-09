package trading.persistence;

import trading.domain.Account.Account;
import trading.domain.Account.AccountNumber;

public interface AccountRepository {
    Account save(Account account);

    Account findByAccountNumber(AccountNumber accountNumber);

    boolean accountAlreadyExists(Long investorId);
}
