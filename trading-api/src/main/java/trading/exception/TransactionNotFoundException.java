package trading.exception;

import trading.domain.TransactionNumber;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(TransactionNumber transactionNumber) {
        super("transaction with number " + transactionNumber.getStringUUID() + " not found");
    }
}