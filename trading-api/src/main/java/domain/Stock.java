package domain;

public class Stock {
    private String market;
    private String symbol;

    public Stock(String market, String symbol) {
        this.market = market;
        this.symbol = symbol;
    }

    public Stock() {

    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
