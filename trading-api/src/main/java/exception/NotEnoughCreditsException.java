package exception;

import domain.transaction.TransactionId;

public class NotEnoughCreditsException extends RuntimeException {
    public TransactionId transactionId;

    public NotEnoughCreditsException(TransactionId transactionId) {
        super("not enough credits in wallet");
        this.transactionId = transactionId;
    }
}

