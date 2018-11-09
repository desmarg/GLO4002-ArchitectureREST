package trading.services;

import trading.application.JerseyClient;
import trading.domain.Credits;
import trading.domain.DateTime;
import trading.domain.Stock;
import trading.domain.transaction.TransactionNumber;
import trading.exception.InvalidDateException;
import trading.exception.StockNotFoundException;
import trading.external.response.StockPriceResponse;
import trading.external.response.StockResponse;

import java.util.UUID;

public class StockService {

    public Credits getStockPrice(Stock stock, DateTime dateTime) {
        String url = "/stocks/" + stock.getMarket() + "/" + stock.getSymbol();
        StockResponse stockDto = JerseyClient.getInstance().getRequest(url, StockResponse.class);
        if (stockDto == null) {
            throw new StockNotFoundException(
                    stock.getSymbol(),
                    stock.getMarket(),
                    new TransactionNumber(UUID.randomUUID())
            );
        }
        return this.getPriceFromDateTime(stockDto, dateTime);
    }

    public Credits getPriceFromDateTime(StockResponse stockDto, DateTime dateTime) {
        for (StockPriceResponse priceInfo : stockDto.getPrices()) {
            if (priceInfo.getDate().truncatedToDays().equals(dateTime.truncatedToDays())) {
                return priceInfo.getPrice();
            }
        }

        throw new InvalidDateException(new TransactionNumber(UUID.randomUUID()));
    }
}
