package trading.domain.Transaction;

import trading.exception.MappedException;

import javax.ws.rs.core.Response.Status;

public class UnsupportedTransactionTypeException extends MappedException {
    public UnsupportedTransactionTypeException(String transactionType) {
        super(
                "UNSUPPORTED_TRANSACTION_TYPE",
                "transaction '" + transactionType + "' is not supported",
                Status.BAD_REQUEST
        );
    }
}
