package trading.domain;

public class Stock {
    private String market;
    private String symbol;

    public String getMarket() {
        return this.market;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public boolean equals(Object comparedObject) {
        if (comparedObject == this) {
            return true;
        }
        if (!(comparedObject instanceof Stock)) {
            return false;
        }
        Stock stock = (Stock) comparedObject;

        return (stock.getMarket().equals(this.market) && stock.getSymbol().equals(this.symbol));
    }
}
