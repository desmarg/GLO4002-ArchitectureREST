package exception;

import domain.transaction.TransactionNumber;

import javax.ws.rs.core.Response.Status;
import java.util.UUID;

public class InvalidTransactionNumberException extends MappedException {

    private UUID transactionNumber;

    public InvalidTransactionNumberException(TransactionNumber transactionNumber) {
        this.error = "INVALID_TRANSACTION_NUMBER";
        this.description = "the transaction number is invalid";
        this.status = Status.BAD_REQUEST;
        this.transactionNumber = transactionNumber.getId();
    }
}