package trading.services;

import trading.application.JerseyClient;
import trading.domain.Credits.Credits;
import trading.domain.DateTime.DateTime;
import trading.domain.DateTime.InvalidDateException;
import trading.domain.Stock;
import trading.domain.transaction.StockNotFoundException;
import trading.domain.transaction.TransactionNumber;
import trading.external.response.StockDTO;
import trading.external.response.StockPriceResponse;

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

    public Credits retrievePriceFromDateTime(final StockDTO stockDto, final DateTime dateTime) {
        for (final StockPriceResponse priceInfo : stockDto.getPrices()) {
            if (priceInfo.getDate().truncatedToDays().equals(dateTime.truncatedToDays())) {
                return priceInfo.getPrice();
            }
        }
        throw new InvalidDateException(new TransactionNumber(UUID.randomUUID()));
    }
}
