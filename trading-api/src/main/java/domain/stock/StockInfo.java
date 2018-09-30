package domain.stock;

import domain.Credits;
import domain.DateTime;
import exception.StockNotFoundException;

import java.util.ArrayList;

public class StockInfo {
    private Long id;
    private String market;
    private String symbol;
    private String type;
    private ArrayList<PriceInfo> prices;

    public Credits getPriceFromDate(DateTime date) {
        for (PriceInfo price : this.prices) {
            DateTime dateTime = new DateTime(price.getDate());

            if (dateTime.isSameDay(date)) {
                return price.getPrice();
            }
        }
        throw new StockNotFoundException(this.symbol, this.market);
    }

    public ArrayList<PriceInfo> getPrices() {
        return this.prices;
    }

    public void setPrices(ArrayList<PriceInfo> prices) {
        this.prices = prices;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarket() {
        return this.market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
