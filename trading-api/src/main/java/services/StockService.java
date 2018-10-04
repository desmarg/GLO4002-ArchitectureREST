package services;

import application.JerseyClient;
import domain.Credits;
import domain.DateTime;
import domain.Stock;
import exception.StockNotFoundException;
import external.response.StockPriceResponse;
import external.response.StockResponse;

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
        StockResponse stockDto = JerseyClient.getInstance().getRequest(url, StockResponse.class);
        if (stockDto == null) {
            throw new StockNotFoundException(stock.getSymbol(), stock.getMarket());
        }
        return this.getPriceFromDate(stockDto, date);
    }

    public Credits getPriceFromDate(StockResponse stockDto, DateTime date) {
        for (StockPriceResponse priceInfo : stockDto.getPrices()) {
            DateTime dateTime = new DateTime(priceInfo.getDate());

            if (dateTime.isSameDay(date)) {
                return priceInfo.getPrice();
            }
        }
        throw new StockNotFoundException(stockDto.getSymbol(), stockDto.getMarket());
    }

}
