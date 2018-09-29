package application;

import domain.Stock;
import domain.StockInfo;

public class StockService {

    public StockService() {
    }

    public double getStockPrice(Stock stock, String date) throws Exception {
        String url = "/stocks/" + stock.getMarket() + "/" + stock.getSymbol();
        StockInfo stockInfo = JerseyClient.getRequest(url, StockInfo.class);
        return stockInfo.getPriceFromDate(date);
    }

}
