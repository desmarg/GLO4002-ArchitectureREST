package trading.exception;

import javax.ws.rs.core.Response.Status;

public class InvalidCreditsAmountException extends MappedException {

    public InvalidCreditsAmountException() {
        this.error = "INVALID_AMOUNT";
        this.description = "credit amount cannot be lower than or equal to zero";
        this.status = Status.BAD_REQUEST;
    }
}