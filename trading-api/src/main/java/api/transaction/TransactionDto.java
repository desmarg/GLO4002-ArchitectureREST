package api.transaction;

import domain.Stock;

import java.util.Date;


public abstract class TransactionDto {
    private Date date;
    private Stock stock;
    private Long quantity;

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
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

    public void setStock(Stock stock){
        this.stock = stock;
    }
}
