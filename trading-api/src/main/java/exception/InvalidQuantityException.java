package exception;

import domain.TransactionNumber;

public class InvalidQuantityException extends RuntimeException {

    private TransactionNumber transactionNumber;

    public InvalidQuantityException(TransactionNumber transactionNumber) {
        super("quantity is invalid");
        this.transactionNumber = transactionNumber;
    }

    public TransactionNumber getTransactionNumber() {
        return this.transactionNumber;
    }
}