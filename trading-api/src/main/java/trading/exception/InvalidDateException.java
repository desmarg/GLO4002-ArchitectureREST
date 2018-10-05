package trading.exception;

import trading.domain.transaction.TransactionNumber;

import javax.ws.rs.core.Response.Status;
import java.util.UUID;

public class InvalidDateException extends MappedException {

    private UUID transactionNumber;

    public InvalidDateException(TransactionNumber transactionNumber) {
        super(
                "INVALID_DATE",
                "the transaction date is invalid",
                Status.BAD_REQUEST
        );
        this.transactionNumber = transactionNumber.getId();
    }

    public UUID getTransactionNumber() {
        return this.transactionNumber;
    }
}