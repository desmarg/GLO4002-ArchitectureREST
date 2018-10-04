package api.transaction;

import domain.stock.Stock;

import java.util.UUID;

public abstract class TransactionDto {
    private String type;
    private String date;
    private Stock stock;
    private UUID transactionNumber;
    private long quantity;

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Stock getStock() {
        return this.stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public UUID getTransactionNumber() {
        return this.transactionNumber;
    }

    public void setTransactionNumber(UUID transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
