package domain.transaction;

import domain.Credits;
import domain.DateTime;
import domain.stock.Stock;

public class Transaction {
    private TransactionNumber transactionNumber;
    private TransactionType transactionType;
    private Long quantity;
    private DateTime date;
    private Stock stock;
    private Credits stockPrice;
    private Credits purchasedPrice;
    private TransactionNumber referredTransactionNumber;


    public Transaction(TransactionType transactionType, Long quantity, DateTime date, Stock stock,
                       Credits stockPrice) {
        this.transactionNumber = new TransactionNumber();
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.date = date;
        this.stock = stock;
        this.stockPrice = stockPrice;
        this.purchasedPrice = this.calculateTransactionPrice();
    }

    public Transaction(TransactionType transactionType, Long quantity, DateTime date, Stock stock,
                       Credits stockPrice, TransactionNumber referredTransactionNumber) {
        this.transactionNumber = new TransactionNumber();
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.date = date;
        this.stock = stock;
        this.stockPrice = stockPrice;
        this.purchasedPrice = this.calculateTransactionPrice();
        this.referredTransactionNumber = referredTransactionNumber;
    }


    public Credits calculateTransactionPrice() {
        return this.stockPrice.multiply(this.quantity);
    }

    public TransactionNumber getTransactionNumber() {
        return this.transactionNumber;
    }

    public String getStringTransactionId() {
        return this.transactionNumber.getStringUUID();
    }

    public Long getQuantity() {
        return this.quantity;
    }

    public Stock getStock() {
        return this.stock;
    }

    public Credits getStockPrice() {
        return this.stockPrice;
    }

    public DateTime getDate() {
        return this.date;
    }

    public TransactionType getTransactionType() {
        return this.transactionType;
    }

    public TransactionNumber getReferredTransactionNumber() {
        return this.referredTransactionNumber;
    }
}
