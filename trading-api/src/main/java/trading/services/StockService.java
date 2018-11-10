package trading.services;

import trading.application.JerseyClient;
import trading.domain.Credits.Credits;
import trading.domain.DateTime.DateTime;
import trading.domain.DateTime.InvalidDateException;
import trading.domain.Stock;
import trading.domain.transaction.StockNotFoundException;
import trading.domain.transaction.TransactionNumber;
import trading.external.response.StockApiDTO;
import trading.external.response.StockPriceResponseDTO;

import java.util.UUID;

public class StockService {

    public Credits retrieveStockPrice(final Stock stock, final DateTime dateTime) {
        final String url = "/stocks/" + stock.getMarket() + "/" + stock.getSymbol();
        final StockDTO stockDto = JerseyClient.getInstance().getRequest(url, StockDTO.class);
        if (stockDto == null) {
            throw new StockNotFoundException(
                    stock.getSymbol(),
                    stock.getMarket(),
                    new TransactionNumber(UUID.randomUUID())
            );
        }
        return this.retrievePriceFromDateTime(stockDto, dateTime);
    }

    public Credits getPriceFromDateTime(StockApiDTO stockDto, DateTime dateTime) {
        for (StockPriceResponseDTO priceInfo : stockDto.prices) {

            DateTime princeInfoDateTime = DateTime.fromInstant(priceInfo.date);

            if (princeInfoDateTime.getDayOfYear().equals(dateTime.getDayOfYear())) {
                return new Credits(priceInfo.price);
            }
        }
        throw new InvalidDateException(new TransactionNumber(UUID.randomUUID()));
    }
}
