package trading.exception;

import javax.ws.rs.core.Response.Status;

public class InvalidCreditsAmountException extends MappedException {

    public InvalidCreditsAmountException() {
        super(
                "INVALID_AMOUNT",
                "credit amount cannot be lower than or equal to zero",
                Status.BAD_REQUEST
        );
    }
}