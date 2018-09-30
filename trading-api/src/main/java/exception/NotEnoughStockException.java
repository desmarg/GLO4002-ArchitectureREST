package exception;

import domain.stock.Stock;
import domain.transaction.Transaction;
import domain.transaction.TransactionNumber;

public class NotEnoughStockException extends RuntimeException {

    private TransactionNumber transactionNumber;
    public NotEnoughStockException(Stock stock, Transaction transaction) {

        super("not enough stock '" + stock.getSymbol() + ":" + stock.getMarket());
        this.transactionNumber = transaction.getTransactionNumber();
    }

    public TransactionNumber getTransactionNumber() {
        return transactionNumber;
    }
}