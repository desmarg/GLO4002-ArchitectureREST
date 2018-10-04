package trading.exception;

import trading.domain.TransactionType;

public class UnsupportedTransactionTypeException extends RuntimeException {

    public UnsupportedTransactionTypeException(TransactionType transactionType) {
        super("transaction " + transactionType.toString() + " is not supported");
    }
}