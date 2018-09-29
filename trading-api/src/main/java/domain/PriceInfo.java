package domain;

public class PriceInfo {
    private DateTime date;
    private Float price;

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public Credits getPrice() {
        return Credits.fromFloat(price);
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}

