package domain.transaction;

import domain.Credits;
import domain.DateTime;
import domain.stock.Stock;

public class Transaction {
    private TransactionId transactionId;
    private TransactionType transactionType;
    private Long quantity;
    private DateTime date;
    private Stock stock;

    public Credits getStockPrice() {
        return stockPrice;
    }

    private Credits stockPrice;
    private Credits transactionPrice;


    public Transaction(TransactionType transactionType, Long quantity, DateTime date, Stock stock,
                       Credits stockPrice, TransactionId transactionId) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.date = date;
        this.stock = stock;
        this.stockPrice = stockPrice;
        this.transactionPrice = this.calculateTransactionPrice();
    }

    public Credits calculateTransactionPrice() {
        return stockPrice.multiply(quantity);
    }

    public TransactionId getTransactionId() {
        return transactionId;
    }

    public String getStringTransactionId() {
        return transactionId.getStringUUID();
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }


}
