package trading.exception;

import trading.domain.AccountNumber;

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
