package trading.domain.transaction;

import trading.domain.transaction.TransactionNumber;
import trading.exception.MappedException;

import javax.ws.rs.core.Response.Status;
import java.util.UUID;

public class InvalidQuantityException extends MappedException {
    private UUID transactionNumber;

    public InvalidQuantityException() {
        super(
                "INVALID_QUANTITY",
                "quantity is invalid",
                Status.BAD_REQUEST
        );
        this.transactionNumber = new TransactionNumber().getId();
    }

    public UUID getTransactionNumber() {
        return this.transactionNumber;
    }
}
