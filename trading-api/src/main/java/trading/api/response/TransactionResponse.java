package trading.api.response;

import trading.domain.DateTime.DateTime;
import trading.domain.Stock;
import trading.domain.Transaction.Transaction;

import java.time.Instant;
import java.util.UUID;

public abstract class TransactionResponse {
    private String type;
    private Instant date;
    private Stock stock;
    private UUID transactionNumber;
    private Long quantity;
    private Float fees;

    public TransactionResponse(Transaction transaction) {
        this.type = transaction.getTransactionType().toString();
        this.date = transaction.getDateTime().toInstant();
        this.stock = transaction.getStock();
        this.transactionNumber = transaction.getTransactionNumber().getId();
        this.quantity = transaction.getQuantity();
        this.fees = transaction.getFees().valueToFloat();
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return this.date.toString();
    }

    public void setDate(DateTime date) {
        this.date = date.toInstant();
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

    public Long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Float getFees() {
        return this.fees;
    }

    public void setFees(Float fees) {
        this.fees = fees;
    }
}
