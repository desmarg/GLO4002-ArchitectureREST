package exception;

import domain.Stock;
import domain.TransactionNumber;

public class NotEnoughStockException extends RuntimeException {

    private TransactionNumber transactionNumber;

    public NotEnoughStockException(Stock stock, TransactionNumber transactionNumber) {

        super("not enough stock '" + stock.getSymbol() + ":" + stock.getMarket());
        this.transactionNumber = transactionNumber;
    }

    public TransactionNumber getTransactionNumber() {
        return this.transactionNumber;
    }
}