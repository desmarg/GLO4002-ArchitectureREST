package trading.external.response;

import java.util.ArrayList;

import trading.domain.Credits;

public class StockResponse {
    private Long id;
    private String market;
    private String symbol;
    private String type;
    private ArrayList<StockPriceResponse> prices;

    public ArrayList<StockPriceResponse> getPrices() {
        return this.prices;
    }

    public void setPrices(ArrayList<StockPriceResponse> prices) {
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

    public String getType() {
        return this.type;
    }

}
