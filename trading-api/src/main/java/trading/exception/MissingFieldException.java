package trading.exception;

import javax.ws.rs.core.Response.Status;

public class MissingFieldException extends MappedException {
    public MissingFieldException(String field) {
        super(
                "INVALID_REQUEST",
                "field (" + field + ") is missing",
                Status.BAD_REQUEST
        );
    }
}
