package exception;

import domain.transaction.TransactionId;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(TransactionId transactionId) {
        super("transaction with number " + transactionId.getStringUUID() + " not found");
    }
}