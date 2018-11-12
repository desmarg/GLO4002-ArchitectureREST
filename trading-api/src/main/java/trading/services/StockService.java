package trading.services;

import trading.application.JerseyClient;
import trading.domain.Credits.Credits;
import trading.domain.DateTime.DateTime;
import trading.domain.DateTime.InvalidDateException;
import trading.domain.Stock;
import trading.domain.transaction.StockNotFoundException;
import trading.external.response.StockApiDTO;
import trading.external.response.StockPriceResponseDTO;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class StockService {

    private final JerseyClient jerseyClient;

    public StockService(JerseyClient jerseyClient) {
        this.jerseyClient = jerseyClient;
    }

    public Credits retrieveStockPrice(Stock stock, DateTime dateTime) {
        String url = "/stocks/" + stock.getMarket() + "/" + stock.getSymbol();
        StockApiDTO stockApiDTO = this.jerseyClient.getRequest(url, StockApiDTO.class);
        if (stockApiDTO == null) {
            throw new StockNotFoundException(
                    stock.getSymbol(),
                    stock.getMarket()
            );
        }
        return this.getPriceFromDateTime(stockApiDTO, dateTime);
    }

    private Credits getPriceFromDateTime(StockApiDTO stockDto, DateTime dateTime) {
        Instant queryInstant = dateTime.truncateTime();
        for (StockPriceResponseDTO priceInfo : stockDto.prices) {
            Instant priceInfoInstant = priceInfo.date.truncatedTo(ChronoUnit.DAYS);
            if (priceInfoInstant.equals(queryInstant)) {
                return new Credits(priceInfo.price);
            }
        }
        throw new InvalidDateException();
    }
}
