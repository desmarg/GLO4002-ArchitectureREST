package trading.domain.transaction;

import trading.exception.MappedException;

import javax.ws.rs.core.Response.Status;

public class InvalidQuantityException extends MappedException {

    public InvalidQuantityException() {
        super(
                "INVALID_QUANTITY",
                "quantity is invalid",
                Status.BAD_REQUEST
        );
    }

}
