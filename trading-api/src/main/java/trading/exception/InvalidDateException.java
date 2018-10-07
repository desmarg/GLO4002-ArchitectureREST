package trading.exception;

import javax.ws.rs.core.Response.Status;

public class InvalidDateException extends MappedException {

    public InvalidDateException(String date) {
        super(
                "INVALID_DATE",
                "date '" + date + "' is invalid",
                Status.BAD_REQUEST
        );
    }
}