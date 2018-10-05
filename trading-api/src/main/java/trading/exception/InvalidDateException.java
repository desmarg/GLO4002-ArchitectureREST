package trading.exception;

import trading.domain.TransactionNumber;

import javax.ws.rs.core.Response.Status;
import java.util.UUID;

public class InvalidDateException extends MappedException {

    private UUID transactionNumber;

    public InvalidDateException(TransactionNumber transactionNumber) {
        this.error = "INVALID_DATE";
        this.description = "the transaction date is invalid";
        this.status = Status.BAD_REQUEST;
        this.transactionNumber = transactionNumber.getId();
    }
}