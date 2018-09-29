package domain;

import exception.StockNotFoundException;

import java.util.ArrayList;

public class StockInfo {
    private Long id;
    private String market;
    private String symbol;
    private String type;
    private ArrayList<PriceInfo> prices;

    public ArrayList<PriceInfo> getPrices() {
        return prices;
    }

    public void setPrices(ArrayList<PriceInfo> prices) {
        this.prices = prices;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Credits getPriceFromDate(DateTime date) {
        for (PriceInfo price : this.prices) {
            if (price.getDate().equals(date)) {
                return price.getPrice();
            }
        }
        throw new StockNotFoundException(this.symbol, this.market);
    }
}
