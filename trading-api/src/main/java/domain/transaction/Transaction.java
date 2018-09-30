package domain.transaction;

import domain.Credits;
import domain.DateTime;
import domain.stock.Stock;

public class Transaction {
    private TransactionId transactionNumber;
    private TransactionType transactionType;
    private Long quantity;
    private DateTime date;
    private Stock stock;
    private Credits stockPrice;
    private Credits purchasedPrice;


    public Transaction(TransactionType transactionType, Long quantity, DateTime date, Stock stock,
                       Credits stockPrice, TransactionId transactionId) {
        this.transactionNumber = transactionId;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.date = date;
        this.stock = stock;
        this.stockPrice = stockPrice;
        this.purchasedPrice = this.calculateTransactionPrice();
    }

    public Credits calculateTransactionPrice() {
        return stockPrice.multiply(quantity);
    }

    public TransactionId getTransactionNumber() {
        return transactionNumber;
    }

    public String getStringTransactionId() {
        return transactionNumber.getStringUUID();
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }


    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Credits getPurchasedPrice() {
        return purchasedPrice;
    }

    public void setPurchasedPrice(Credits purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }

    public Credits getStockPrice() {
        return stockPrice;
    }

    public DateTime getDate() {
        return date;
    }
}
