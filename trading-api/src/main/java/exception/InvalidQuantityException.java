package exception;

import domain.transaction.TransactionNumber;

import javax.ws.rs.core.Response.Status;
import java.util.UUID;

public class InvalidQuantityException extends MappedException {

    private UUID transactionNumber;

    public InvalidQuantityException(TransactionNumber transactionNumber) {
        this.error = "INVALID_QUANTITY";
        this.description = "quantity is invalid";
        this.status = Status.BAD_REQUEST;
        this.transactionNumber = transactionNumber.getId();
    }
}