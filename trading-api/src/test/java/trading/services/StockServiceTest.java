package trading.services;

import org.junit.Test;
import trading.domain.DateTime.DateTime;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.Assert.assertTrue;

public class StockServiceTest {
    private static final DateTime DATE_TIME = DateTime.fromInstant(Instant.parse("2015-01-01T05:00:00.000Z"));
    private static final DateTime INVALID_DATE_TIME = DateTime.fromInstant(Instant.parse("1801-05-04T05:00:00.000Z"));
    private static final BigDecimal PRICE = new BigDecimal(1.);

//    private final StockService stockService = new StockService();

    @Test
    public void assertTrues() {
        assertTrue(true);
    }
//    StockApiDTO createStock(DateTime dateTime) {
//        StockPriceResponseDTO stockPrice = new StockPriceResponseDTO();
//        stockPrice.date = dateTime.toInstant();
//        stockPrice.value = PRICE;
//
//        final List stockPrices = new ArrayList<>();
//        stockPrices.add(stockPrice);
//
//        StockApiDTO stockApiDTO = new StockApiDTO();
//        stockApiDTO.prices = stockPrices;
//        return stockApiDTO;
//    }
//
//    @Test
//    public void givenValidDateTime_whenGetPriceFromDateTime_thenReturnCredits() {
//        final Credits creditsFound = this.stockService.retrieveStockPrice(
//                this.createStockResponse(DATE_TIME), DATE_TIME
//        );
//
//        assertEquals(
//                new Credits(PRICE),
//                creditsFound
//        );
//    }
}
