package exception;

import domain.stock.Stock;
import domain.transaction.TransactionNumber;

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