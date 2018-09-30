package api.transaction;

import domain.DateTime;
import domain.stock.Stock;

public abstract class TransactionDto {
    private Stock stock;
    private Long quantity;

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
