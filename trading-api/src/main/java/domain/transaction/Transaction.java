package domain.transaction;

import domain.Credits;
import domain.DateTime;
import domain.account.Account;
import domain.stock.Stock;

public abstract class Transaction {
    protected TransactionNumber transactionNumber;
    protected TransactionType transactionType;
    protected Long quantity;
    protected DateTime date;
    protected Stock stock;
    protected Credits stockPrice;
    protected Credits price;


    protected Transaction(Long quantity, DateTime date, Stock stock,
                          Credits stockPrice) {
        this.transactionNumber = new TransactionNumber();
        this.quantity = quantity;
        this.date = date;
        this.stock = stock;
        this.stockPrice = stockPrice;
        this.price = this.calculateTransactionPrice();
    }

    public abstract void make(Account account);

    public Credits calculateTransactionPrice() {
        Credits transactionPrice = new Credits(this.stockPrice);
        transactionPrice.multiply(this.quantity);
        return transactionPrice;
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
}
