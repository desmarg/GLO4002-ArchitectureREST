package domain;

public class Stock {
    private String market;
    private String symbol;

    public Stock(String market, String symbol){
        this.market = market;
        this.symbol = symbol;
    }

    public String getSymbol() { return this.symbol; }

    public String getMarket() { return this.market; }
}
