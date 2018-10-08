package trading.exception;

import javax.ws.rs.core.Response.Status;

public class AccountAlreadyExistsException extends MappedException {

    public AccountAlreadyExistsException(Long investorId) {
        super(
                "ACCOUNT_ALREADY_OPEN",
                "account already open for investor " + investorId,
                Status.BAD_REQUEST
        );

    }
}
