package trading.exception;

import trading.domain.TransactionType;

import javax.ws.rs.core.Response.Status;

public class UnsupportedTransactionTypeException extends MappedException {

    public UnsupportedTransactionTypeException(TransactionType transactionType) {
        this.error = "UNSUPPORTED_TRANSACTION_TYPE";
        this.description = "transaction " + transactionType.toString() + " is not supported";
        this.status = Status.BAD_REQUEST;
    }
}