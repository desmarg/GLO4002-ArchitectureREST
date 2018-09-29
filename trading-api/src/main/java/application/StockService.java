package application;

import domain.Credits;
import domain.DateTime;
import domain.Stock;
import domain.StockInfo;
import exception.StockNotFoundException;

public class StockService {

    public static Credits getStockPrice(Stock stock, DateTime date) {
        String url = "/stocks/" + stock.getMarket() + "/" + stock.getSymbol();
        StockInfo stockInfo = JerseyClient.getRequest(url, StockInfo.class);
        if (stockInfo == null) {
            throw new StockNotFoundException(stock.getSymbol(), stock.getMarket());
        }
        return stockInfo.getPriceFromDate(date);
    }

}
