package application.stock;

import domain.Credits;

public class StockPrice {
    private String date;
    private Float price;

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Credits getPrice() {
        return Credits.fromDouble(this.price);
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}

