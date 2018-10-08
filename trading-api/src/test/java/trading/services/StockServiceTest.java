package trading.services;

import org.junit.Before;
import org.junit.Test;
import trading.domain.Credits;
import trading.domain.DateTime;
import trading.external.response.StockPriceResponse;
import trading.external.response.StockResponse;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class StockServiceTest {
    private static String DATE_STRING = "2015-01-01T05:00:00.000Z";
    private static DateTime DATETIME = new DateTime(DATE_STRING);
    private static Double PRICE = 10d;

    private StockService stockService;
    private StockResponse stockResponse;
    private StockPriceResponse stockPrice;
    private ArrayList<StockPriceResponse> stockPrices;

    @Before
    public void setup() {
        this.stockService = new StockService();

        this.stockPrice = new StockPriceResponse();
        this.stockPrice.setDate(DATETIME);
        this.stockPrice.setPrice(PRICE);

        this.stockPrices = new ArrayList<>();
        this.stockPrices.add(this.stockPrice);

        this.stockResponse = new StockResponse();
        this.stockResponse.setPrices(this.stockPrices);
    }

    @Test
    public void givenValidDateTime_whenGetPriceFromDate_thenReturnCredits() {
        Credits creditsFound = this.stockService.getPriceFromDateTime(
                this.stockResponse, DATETIME
        );

        assertEquals(
                this.stockPrice.getPrice().valueToString(),
                creditsFound.valueToString()
        );
    }
}
