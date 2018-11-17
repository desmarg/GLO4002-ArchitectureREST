package trading.persistence;

import trading.domain.account.AccountNumber;
import trading.exception.MappedException;

import javax.ws.rs.core.Response.Status;

public class AccountNotFoundException extends MappedException {
    public AccountNotFoundException(AccountNumber accountNumber) {
        super(
                "ACCOUNT_NOT_FOUND",
                "account with number " + accountNumber.getString() + " not found",
                Status.NOT_FOUND
        );
    }
}
