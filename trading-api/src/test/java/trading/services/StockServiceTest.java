package trading.services;

import org.junit.Test;
import trading.domain.Credits.Credits;
import trading.domain.DateTime.DateTime;
import trading.external.response.StockDTO;
import trading.external.response.StockPriceResponse;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StockServiceTest {
    private static final DateTime DATE_TIME = new DateTime("2015-01-01T05:00:00.000Z");
    private static final DateTime INVALID_DATE_TIME = new DateTime("1801-05-04T05:00:00.000Z");
    private static final Credits PRICE = Credits.fromDouble(1.);

    private final StockService stockService = new StockService();

    StockDTO createStockResponse(final DateTime dateTime) {
        final StockPriceResponse stockPrice = new StockPriceResponse();
        stockPrice.setDate(dateTime);
        stockPrice.setPrice(PRICE);

        final List stockPrices = new ArrayList<>();
        stockPrices.add(stockPrice);

        final StockDTO stockDTO = new StockDTO();
        stockDTO.setPrices(stockPrices);
        return stockDTO;
    }

    @Test
    public void givenValidDateTime_whenGetPriceFromDateTime_thenReturnCredits() {
        final Credits creditsFound = this.stockService.retrievePriceFromDateTime(
                this.createStockResponse(DATE_TIME), DATE_TIME
        );

        assertEquals(
                PRICE,
                creditsFound
        );
    }
}
