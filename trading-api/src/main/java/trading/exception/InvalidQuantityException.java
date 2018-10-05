package trading.exception;

import trading.domain.transaction.TransactionNumber;

import javax.ws.rs.core.Response.Status;
import java.util.UUID;

public class InvalidQuantityException extends MappedException {

    private UUID transactionNumber;

    public InvalidQuantityException(TransactionNumber transactionNumber) {
        super(
                "INVALID_QUANTITY",
                "quantity is invalid",
                Status.BAD_REQUEST
        );
        this.transactionNumber = transactionNumber.getId();
    }
}