package trading.services;

import trading.application.JerseyClient;
import trading.domain.Credits.Credits;
import trading.domain.DateTime.DateTime;
import trading.domain.Stock;
import trading.domain.transaction.TransactionNumber;
import trading.domain.DateTime.InvalidDateException;
import trading.domain.transaction.StockNotFoundException;
import trading.external.response.StockDTO;
import trading.external.response.StockPriceResponse;

import java.util.UUID;

public class StockService {

    public Credits getStockPrice(Stock stock, DateTime dateTime) {
        String url = "/stocks/" + stock.getMarket() + "/" + stock.getSymbol();
        StockDTO stockDto = JerseyClient.getInstance().getRequest(url, StockDTO.class);
        if (stockDto == null) {
            throw new StockNotFoundException(
                    stock.getSymbol(),
                    stock.getMarket(),
                    new TransactionNumber(UUID.randomUUID())
            );
        }
        return this.getPriceFromDateTime(stockDto, dateTime);
    }

    public Credits getPriceFromDateTime(StockDTO stockDto, DateTime dateTime) {
        for (StockPriceResponse priceInfo : stockDto.getPrices()) {

            DateTime princeInfoDateTime = DateTime.fromInstant(priceInfo.getDate());

            if (princeInfoDateTime.getDayOfYear().equals(dateTime.getDayOfYear())) {
                return priceInfo.getPrice();
            }
        }
        throw new InvalidDateException(new TransactionNumber(UUID.randomUUID()));
    }
}
