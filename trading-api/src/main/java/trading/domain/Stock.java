package trading.domain;

public class Stock {
    private final String market;
    private final String symbol;

    public Stock(String market, String symbol) {
        this.market = market;
        this.symbol = symbol;
    }

    public String getMarket() {
        return this.market;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public boolean equals(Object comparedObject) {
        if (this == comparedObject) {
            return true;
        }
        if (!(comparedObject instanceof Stock)) {
            return false;
        }
        Stock stock = (Stock) comparedObject;

        return (stock.getMarket().equals(this.market) && stock.getSymbol().equals(this.symbol));
    }

    @Override
    public int hashCode() {
        return this.market.hashCode() + this.symbol.hashCode();
    }
}
