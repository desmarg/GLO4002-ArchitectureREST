package trading.external.response;

import trading.domain.Credits.Credits;

import java.time.Instant;

public class StockPriceResponse {
    private Instant date;
    private Credits price;

    public Instant getDate() {
        return this.date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Credits getPrice() {
        return this.price;
    }

    public void setPrice(Credits price) {
        this.price = price;
    }
}
