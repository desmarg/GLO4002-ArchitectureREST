package trading.domain.datetime;

import trading.exception.MappedException;

import javax.ws.rs.core.Response;

public class MissingDateException extends MappedException {

    public MissingDateException() {
        super("MISSING_DATE", "date is missing", Response.Status.BAD_REQUEST);
    }
}
