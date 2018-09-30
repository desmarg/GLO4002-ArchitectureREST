package exception;

import domain.transaction.TransactionNumber;

public class NotEnoughCreditsException extends RuntimeException {
    public TransactionNumber transactionNumber;

    public NotEnoughCreditsException(TransactionNumber transactionNumber) {
        super("not enough credits in wallet");
        this.transactionNumber = transactionNumber;
    }
}

