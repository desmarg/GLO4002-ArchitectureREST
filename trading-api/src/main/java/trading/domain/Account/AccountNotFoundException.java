package trading.domain.Account;

import trading.exception.MappedException;

import javax.ws.rs.core.Response.Status;

public class AccountNotFoundException extends MappedException {
    public AccountNotFoundException(Long accountId) {
        super(
                "ACCOUNT_NOT_FOUND",
                "account with number " + accountId + " not found",
                Status.NOT_FOUND
        );
    }
}
