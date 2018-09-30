package exception;

import domain.transaction.TransactionNumber;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(TransactionNumber transactionNumber) {
        super("transaction with number " + transactionNumber.getStringUUID() + " not found");
    }
}