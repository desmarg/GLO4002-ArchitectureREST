package trading.services;


import org.junit.Before;
import org.junit.Test;
import trading.domain.Credits;
import trading.domain.DateTime;
import trading.external.api.StockExternalApi;
import trading.external.response.StockPriceResponse;
import trading.external.response.StockResponse;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class StockServiceTest {
    private static String DATE = "2015-01-01T05:00:00.000Z";
    private static DateTime DATETIME = new DateTime(DATE);
    private static Double PRICE = 10d;

    private StockResponse stockDto;
    private StockPriceResponse stockPrice;
    private ArrayList<StockPriceResponse> stockPrices;

    @Before
    public void setup() {

        this.stockPrice = new StockPriceResponse();
        this.stockPrice.setDate(DATE);
        this.stockPrice.setPrice(PRICE);

        this.stockPrices = new ArrayList<>();
        this.stockPrices.add(this.stockPrice);

        this.stockDto = new StockResponse();
        this.stockDto.setPrices(this.stockPrices);
    }

    @Test
    public void givenValidDateTime_whenGetPriceFromDate_thenReturnCredits() {
        Credits creditsFound = StockExternalApi.getPriceFromDate(this.stockDto, DATETIME);
        assertEquals(this.stockPrice.getPrice().valueToString(), creditsFound.valueToString());
    }
}