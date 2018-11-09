package trading.external.response;

import trading.domain.Credits;
import trading.domain.DateTime;

public class StockPriceResponse {
    private DateTime date;
    private Credits price;

    public DateTime getDate() {
        return this.date;
    }

    public void setDate(DateTime dateTime) {
        this.date = dateTime;
    }

    public Credits getPrice() {
        return this.price;
    }

    public void setPrice(Credits price) {
        this.price = price;
    }
}
