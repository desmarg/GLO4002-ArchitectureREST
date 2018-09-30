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
        return stockPrice.multiply(quantity);
    }

    public TransactionNumber getTransactionNumber() {
        return transactionNumber;
    }

    public String getStringTransactionId() {
        return transactionNumber.getStringUUID();
    }

    public Long getQuantity() {
        return quantity;
    }

    public Stock getStock() {
        return stock;
    }

    public Credits getPurchasedPrice() {
        return purchasedPrice;
    }

    public Credits getStockPrice() {
        return stockPrice;
    }

    public DateTime getDate() {
        return date;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }
    public TransactionNumber getReferredTransactionNumber() {
        return referredTransactionNumber;
    }
}
