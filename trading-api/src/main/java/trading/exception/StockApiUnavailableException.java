package trading.exception;

import javax.ws.rs.core.Response.Status;

public class StockApiUnavailableException extends MappedException {

    public StockApiUnavailableException() {
        super(
                "ACCOUNT_ALREADY_OPEN",
                "account already open for investor ",
                Status.BAD_REQUEST
        );

    }
}