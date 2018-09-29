package api.transaction;

import domain.DateTime;
import domain.Stock;


public abstract class TransactionDto {
    private DateTime date;
    private Stock stock;
    private Long quantity;

    public DateTime getDate() {
        return this.date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public Stock getStock() {
        return this.stock;
    }

    public Long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
