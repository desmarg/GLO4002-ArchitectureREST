package trading.domain.datetime;

import trading.exception.MappedException;

import javax.ws.rs.core.Response.Status;

public class InvalidDateException extends MappedException {

    public InvalidDateException() {
        super("INVALID_DATE", "the transaction date is invalid", Status.BAD_REQUEST);
    }
}
