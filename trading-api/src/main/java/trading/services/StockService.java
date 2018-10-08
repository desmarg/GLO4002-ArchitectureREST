package trading.services;

import trading.application.JerseyClient;
import trading.domain.Credits;
import trading.domain.Stock;
import trading.domain.DateTime;
import trading.domain.transaction.TransactionNumber;
import trading.exception.StockNotFoundException;
import trading.external.response.StockPriceResponse;
import trading.external.response.StockResponse;
import trading.exception.InvalidDateException;

import java.util.UUID;

public class StockService {

    private static StockService INSTANCE = null;

    public static StockService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StockService();
        }

        return INSTANCE;
    }

    public Credits getStockPrice(Stock stock, DateTime dateTime) {
        String url = "/stocks/" + stock.getMarket() + "/" + stock.getSymbol();
        StockResponse stockDto = JerseyClient.getInstance().getRequest(url, StockResponse.class);
        if (stockDto == null) {
            throw new StockNotFoundException(stock.getSymbol(), stock.getMarket(), new TransactionNumber(UUID.randomUUID()));
        }
        return this.getPriceFromDateTime(stockDto, dateTime);
    }

    public Credits getPriceFromDateTime(StockResponse stockDto, DateTime dateTime) {
        for (StockPriceResponse priceInfo : stockDto.getPrices()) {
            // Truncate the dateTime do DAYS to check if they represent
            // the same day.
            if (priceInfo.getDate().truncatedToDays()
                    .equals(dateTime.truncatedToDays())) {
                return priceInfo.getPrice();
            }
        }
        throw new InvalidDateException(
                new TransactionNumber(UUID.randomUUID())
        );
    }

}
