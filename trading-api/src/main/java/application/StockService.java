package application;

import domain.Credits;
import domain.DateTime;
import domain.stock.Stock;
import domain.stock.StockInfo;
import exception.StockNotFoundException;

public class StockService {

    private static StockService INSTANCE = null;

    public static StockService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StockService();
        }

        return INSTANCE;
    }

    public Credits getStockPrice(Stock stock, DateTime date) {
        String url = "/stocks/" + stock.getMarket() + "/" + stock.getSymbol();
        StockInfo stockInfo = JerseyClient.getInstance().getRequest(url, StockInfo.class);
        if (stockInfo == null) {
            throw new StockNotFoundException(stock.getSymbol(), stock.getMarket());
        }
        return stockInfo.getPriceFromDate(date);
    }

}
