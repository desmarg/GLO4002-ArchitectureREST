package exception;

public class UnsupportedTransactionTypeException extends RuntimeException {

    public UnsupportedTransactionTypeException(String transactionType) {
        super("transaction " + transactionType + " is not supported");
    }
}