package trading.services;

import org.junit.Test;
import trading.domain.Credits.Credits;
import trading.domain.DateTime.DateTime;
import trading.external.response.StockApiDTO;
import trading.external.response.StockPriceResponseDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StockServiceTest {
    private static final DateTime DATE_TIME = new DateTime("2015-01-01T05:00:00.000Z");
    private static final DateTime INVALID_DATE_TIME = new DateTime("1801-05-04T05:00:00.000Z");
    private static final BigDecimal PRICE = new BigDecimal(1.);

    private StockService stockService = new StockService();

    StockApiDTO createStockResponse(DateTime dateTime) {
        StockPriceResponseDTO stockPrice = new StockPriceResponseDTO();
        stockPrice.date = dateTime.toInstant();
        stockPrice.price = PRICE;

        List stockPrices = new ArrayList<>();
        stockPrices.add(stockPrice);

        StockApiDTO stockDTO = new StockApiDTO();
        stockDTO.prices = stockPrices;
        return stockDTO;
    }

    @Test
    public void givenValidDateTime_whenGetPriceFromDateTime_thenReturnCredits() {
        Credits creditsFound = this.stockService.getPriceFromDateTime(
                this.createStockResponse(DATE_TIME), DATE_TIME
        );

        assertEquals(
                new Credits(PRICE),
                creditsFound
        );
    }
}
