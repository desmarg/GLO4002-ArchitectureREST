package exception;

import domain.transaction.TransactionNumber;

public class InvalidQuantityException extends RuntimeException{

    private TransactionNumber transactionNumber;

    public InvalidQuantityException(TransactionNumber transactionNumber) {
        super("quantity is invalid");
        this.transactionNumber = transactionNumber;
    }

    public TransactionNumber getTransactionNumber() {
        return transactionNumber;
    }
}