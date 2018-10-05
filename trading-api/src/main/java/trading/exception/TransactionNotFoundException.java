package trading.exception;

import trading.domain.TransactionNumber;

import javax.ws.rs.core.Response.Status;

public class TransactionNotFoundException extends MappedException {

    public TransactionNotFoundException(TransactionNumber transactionNumber) {
        this.error = "TRANSACTION_NOT_FOUND";
        this.description = "transaction with number " + transactionNumber.getId() + " not found";
        this.status = Status.BAD_REQUEST;
    }
}