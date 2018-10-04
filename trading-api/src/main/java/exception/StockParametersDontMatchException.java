package exception;

import domain.TransactionNumber;

public class StockParametersDontMatchException extends RuntimeException {

    private TransactionNumber transactionNumber;

    public StockParametersDontMatchException(TransactionNumber transactionNumber) {

        super("stock parameters don't match existing ones");
        this.transactionNumber = transactionNumber;
    }

    public TransactionNumber getTransactionNumber() {
        return this.transactionNumber;
    }
}