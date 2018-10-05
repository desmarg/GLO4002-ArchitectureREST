package trading.exception;

import javax.ws.rs.core.Response.Status;

public class AccountAlreadyExistsException extends MappedException {

    public AccountAlreadyExistsException(Long investorId) {
        this.error = "ACCOUNT_ALREADY_OPEN";
        this.description = "account already open for investor " + investorId;
        this.status = Status.BAD_REQUEST;
    }
}