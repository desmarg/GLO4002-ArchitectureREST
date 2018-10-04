package exception;

import domain.TransactionNumber;

public class NotEnoughCreditsException extends RuntimeException {

    private TransactionNumber transactionNumber;

    public NotEnoughCreditsException(TransactionNumber transactionNumber) {
        super("not enough credits in wallet");
        this.transactionNumber = transactionNumber;
    }

    public TransactionNumber getTransactionNumber() {
        return this.transactionNumber;
    }
}

