package trading.exception;

import javax.ws.rs.core.Response.Status;

public class InvalidAccountInfoException extends MappedException {

    public InvalidAccountInfoException(String message) {
        super(
                "INVALID_ACCOUNT_INFO",
                message,
                Status.BAD_REQUEST
        );
    }
}
