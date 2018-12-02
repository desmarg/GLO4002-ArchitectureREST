package trading.services;

import trading.api.request.StockDTO;
import trading.application.JerseyClient;
import trading.domain.Credits;
import trading.domain.Currency;
import trading.domain.Stock;
import trading.domain.datetime.DateTime;
import trading.domain.datetime.InvalidDateException;
import trading.domain.transaction.StockNotFoundException;
import trading.external.response.StockApiDTO;
import trading.external.response.StockPriceResponseDTO;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class StockService {

    private final JerseyClient jerseyClient;
    private final HashMap<String, Currency> marketCurrency;

    public StockService(JerseyClient jerseyClient) {
        this.jerseyClient = jerseyClient;
        this.marketCurrency = new HashMap<>();
        this.marketCurrency.put("NASDAQ", Currency.USD);
        this.marketCurrency.put("XTKS", Currency.JPY);
        this.marketCurrency.put("XSWX", Currency.CHF);
        this.marketCurrency.put("NYSE", Currency.USD);
    }

    public Credits retrieveStockPrice(StockDTO stock, DateTime dateTime) {
        String url = "/stocks/" + stock.market + "/" + stock.symbol;
        StockApiDTO stockApiDTO = this.jerseyClient.getRequest(url, StockApiDTO.class);
        if (stockApiDTO == null) {
            throw new StockNotFoundException(stock.symbol, stock.market);
        }
        return this.getPriceFromDateTime(stockApiDTO, dateTime);
    }

    public Credits retrieveStockPrice(Stock stock, DateTime dateTime) {
        String url = "/stocks/" + stock.getMarket() + "/" + stock.getSymbol();
        StockApiDTO stockApiDTO = this.jerseyClient.getRequest(url, StockApiDTO.class);
        if (stockApiDTO == null) {
            throw new StockNotFoundException(stock.getSymbol(), stock.getMarket());
        }
        return this.getPriceFromDateTime(stockApiDTO, dateTime);
    }

    private Credits getPriceFromDateTime(StockApiDTO stockDto, DateTime dateTime) {
        Instant queryInstant = dateTime.toInstant();
        queryInstant = queryInstant.minus(1, ChronoUnit.DAYS);
        queryInstant = queryInstant.truncatedTo(ChronoUnit.DAYS);
        for (StockPriceResponseDTO priceInfo : stockDto.prices) {
            Instant priceInfoInstant = priceInfo.date.truncatedTo(ChronoUnit.DAYS);
            if (priceInfoInstant.equals(queryInstant)) {
                return new Credits(priceInfo.price, getStockCurrency(stockDto.market));
            }
        }
        throw new InvalidDateException();
    }

    private Currency getStockCurrency(String market) {
        if (this.marketCurrency.containsKey(market)) {
            return this.marketCurrency.get(market);
        }
        return Currency.XXX;
    }
}
