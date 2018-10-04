package trading.exception;

import trading.domain.TransactionNumber;

public class InvalidTransactionNumberException extends RuntimeException {

    private TransactionNumber transactionNumber;

    public InvalidTransactionNumberException(TransactionNumber transactionNumber) {
        super("the transaction number is invalid");
        this.transactionNumber = transactionNumber;
    }

    public TransactionNumber getTransactionNumber() {
        return this.transactionNumber;
    }
}