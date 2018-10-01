package application.stock;

import domain.Credits;
import domain.DateTime;
import exception.StockNotFoundException;

import java.util.ArrayList;

public class StockDTO {
    private Long id;
    private String market;
    private String symbol;
    private String type;
    private ArrayList<PriceDTO> prices;

    public Credits getPriceFromDate(DateTime date) {
        for (PriceDTO priceInfo : this.prices) {
            DateTime dateTime = new DateTime(priceInfo.getDate());

            if (dateTime.isSameDay(date)) {
                return priceInfo.getPrice();
            }
        }
        throw new StockNotFoundException(this.symbol, this.market);
    }

    public ArrayList<PriceDTO> getPrices() {
        return this.prices;
    }

    public void setPrices(ArrayList<PriceDTO> prices) {
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
