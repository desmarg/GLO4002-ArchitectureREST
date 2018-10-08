package trading.external.response;

import trading.domain.Credits;
import trading.domain.DateTime;

public class StockPriceResponse {
    private DateTime date;
    private Double price;

    public DateTime getDate() {
        return this.date;
    }

    public void setDate(DateTime dateTime) {
        this.date = dateTime;
    }

    public Credits getPrice() {
        return Credits.fromDouble(this.price);
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
