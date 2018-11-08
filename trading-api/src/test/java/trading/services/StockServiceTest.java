package trading.services;

import org.junit.Before;
import org.junit.Test;
import trading.domain.Credits;
import trading.domain.DateTime;
import trading.external.response.StockPriceResponse;
import trading.external.response.StockResponse;
import trading.exception.InvalidDateException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StockServiceTest {
    private static final DateTime DATE_TIME = new DateTime("2015-01-01T05:00:00.000Z");
    private static final DateTime INVALID_DATE_TIME = new DateTime("1801-05-04T05:00:00.000Z");
    private static final Credits PRICE = Credits.fromDouble(1.);

    private StockService stockService = new StockService();

    StockResponse createStockResponse(DateTime dateTime) {
        StockPriceResponse stockPrice = new StockPriceResponse();
        stockPrice.setDate(dateTime);
        stockPrice.setPrice(PRICE);

        List stockPrices = new ArrayList<>();
        stockPrices.add(stockPrice);

        StockResponse stockResponse = new StockResponse();
        stockResponse.setPrices(stockPrices);
        return stockResponse;
    }

    @Test
    public void givenValidDateTime_whenGetPriceFromDateTime_thenReturnCredits() {
        Credits creditsFound = this.stockService.getPriceFromDateTime(
                createStockResponse(DATE_TIME), DATE_TIME
        );

        assertEquals(
                PRICE,
                creditsFound
        );
    }
}
