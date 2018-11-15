package trading.domain.Report;

import trading.domain.Credits.Credits;

public class StockPortfolio {
    private final Credits actualPrice;
    private Long quantity;

    public StockPortfolio(Credits actualPrice, Long quantity) {
        this.actualPrice = actualPrice;
        this.quantity = quantity;
    }

    public void substract(Long quantity) {
        this.quantity -= quantity;
    }

    public Credits getValue() {
        Credits value = new Credits();
        value.add(this.actualPrice);
        value.multiply(this.quantity);
        return value;
    }
}

