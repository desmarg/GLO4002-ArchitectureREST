package trading.services;

import trading.application.JerseyClient;
import trading.domain.Credits;
import trading.domain.DateTime;
import trading.domain.Stock;
import trading.domain.TransactionNumber;
import trading.exception.StockNotFoundException;
import trading.external.response.StockPriceResponse;
import trading.external.response.StockResponse;

import java.util.UUID;

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
            throw new StockNotFoundException(stock.getSymbol(), stock.getMarket(), new TransactionNumber(UUID.randomUUID()));
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
        throw new StockNotFoundException(stockDto.getSymbol(), stockDto.getMarket(), new TransactionNumber(UUID.randomUUID()));
    }

}
