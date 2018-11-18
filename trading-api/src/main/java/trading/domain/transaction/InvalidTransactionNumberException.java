package trading.domain.transaction;

import trading.exception.MappedException;

import javax.ws.rs.core.Response.Status;

public class InvalidTransactionNumberException extends MappedException {

    public InvalidTransactionNumberException() {
        super("INVALID_TRANSACTION_NUMBER", "the transaction number is invalid",
                Status.BAD_REQUEST);
    }
}
