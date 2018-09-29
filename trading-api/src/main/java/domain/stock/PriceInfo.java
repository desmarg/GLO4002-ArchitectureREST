package domain.stock;

import domain.Credits;

public class PriceInfo {
    private String date;
    private Float price;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Credits getPrice() {
        return Credits.fromFloat(price);
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}

