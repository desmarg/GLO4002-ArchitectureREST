package trading.domain.Account;

import trading.domain.Account.AccountNumber;
import trading.exception.MappedException;

import javax.ws.rs.core.Response.Status;

public class AccountNotFoundException extends MappedException {
    public AccountNotFoundException(AccountNumber accountNumber) {
        super(
                "ACCOUNT_NOT_FOUND",
                "account with number " + accountNumber.getId() + " not found",
                Status.NOT_FOUND
        );
    }
}
