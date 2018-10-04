package domain.stock;

public class Stock {
    private String market;
    private String symbol;

    public String getMarket() {
        return this.market;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Stock)) {
            return false;
        }
        Stock stock = (Stock) o;
        return (stock.getMarket().equals(this.market) && stock.getSymbol().equals(this.symbol));
    }
}
