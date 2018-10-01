package application.stock;

import java.util.ArrayList;

public class StockDto {
    private Long id;
    private String market;
    private String symbol;
    private String type;
    private ArrayList<StockPrice> prices;

    public ArrayList<StockPrice> getPrices() {
        return this.prices;
    }

    public void setPrices(ArrayList<StockPrice> prices) {
        this.prices = prices;
    }

    public Long getId() {
        return this.id;
    }

    public String getMarket() {
        return this.market;
    }

    public String getSymbol() {
        return this.symbol;
    }

}
