package trading.api.request;

import trading.domain.DateTime;
import trading.domain.Stock;
import trading.domain.transaction.TransactionNumber;
import trading.domain.transaction.TransactionType;
import trading.exception.MissingFieldException;

import java.util.UUID;

public class TransactionPostRequest {
    private String type;
    private DateTime date;
    private Stock stock;
    private TransactionNumber transactionNumber;
    private Long quantity;

    public TransactionPostRequest() {
    }

    public void nullCheck() {
        if (this.type == null) {
            throw new MissingFieldException("type");
        }
        if (this.date == null) {
            throw new MissingFieldException("date");
        }
        if (this.stock == null) {
            throw new MissingFieldException("stock");
        }
        if (TransactionType.fromString(type) == TransactionType.SELL) {
            if (this.transactionNumber == null) {
                throw new MissingFieldException("transactionNumber");
            }
        }
        if (this.quantity == null) {
            throw new MissingFieldException("quantity");
        }
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DateTime getDate() {
        return this.date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public Stock getStock() {
        return this.stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public TransactionNumber getTransactionNumber() {
        return this.transactionNumber;
    }

    public void setTransactionNumber(TransactionNumber transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public Long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
