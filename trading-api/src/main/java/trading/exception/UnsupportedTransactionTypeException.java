package trading.exception;

import trading.domain.transaction.TransactionType;

import javax.ws.rs.core.Response.Status;

public class UnsupportedTransactionTypeException extends MappedException {

    public UnsupportedTransactionTypeException(TransactionType transactionType) {
        super(
                "UNSUPPORTED_TRANSACTION_TYPE",
                "transaction " + transactionType.toString() + " is not supported",
                Status.BAD_REQUEST
        );
    }
}