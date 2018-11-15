package trading.domain.Report;

import trading.domain.Credits.Credits;
import trading.domain.Stock;

public class StockPortfolio {
    private Stock stock;
    private Credits price;
    private Long amount;

    public StockPortfolio(Stock stock, Credits price, Long amount) {
        this.stock = stock;
        this.price = price;
        this.amount = amount;
    }

    public void deduct(Long amount) {
        this.amount = this.amount - amount;
    }

    public Credits value() {
        Credits value = new Credits();
        return value;
    }

}

