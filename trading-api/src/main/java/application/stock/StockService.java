package application.stock;

import application.JerseyClient;
import domain.Credits;
import domain.DateTime;
import domain.stock.Stock;
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
        StockDTO stockInfo = JerseyClient.getInstance().getRequest(url, StockDTO.class);
        if (stockInfo == null) {
            throw new StockNotFoundException(stock.getSymbol(), stock.getMarket());
        }
        return stockInfo.getPriceFromDate(date);
    }

}
