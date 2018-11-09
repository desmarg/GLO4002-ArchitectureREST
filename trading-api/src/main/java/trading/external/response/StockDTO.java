package trading.external.response;

import java.util.List;

public class StockDTO {
    private Long id;
    private String market;
    private String symbol;
    private String type;
    private List<StockPriceResponse> prices;

    public List<StockPriceResponse> getPrices() {
        return this.prices;
    }

    public void setPrices(List<StockPriceResponse> prices) {
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
