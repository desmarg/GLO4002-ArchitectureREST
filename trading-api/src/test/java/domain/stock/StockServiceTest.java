package domain.stock;

import domain.Credits;
import domain.DateTime;
import external.response.StockPriceResponse;
import external.response.StockResponse;
import org.junit.Before;
import org.junit.Test;
import services.StockService;

import java.util.ArrayList;

public class StockServiceTest {
    private static String DATE = "2015-01-01T05:00:00.000Z";
    private static DateTime DATETIME = new DateTime(DATE);
    private static Double PRICE = 10d;

    private StockService stockService;
    private StockResponse stockDto;
    private StockPriceResponse stockPrice;
    private ArrayList<StockPriceResponse> stockPrices;

    @Before
    public void setup() {
        this.stockService = new StockService();

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
        Credits creditsFound = this.stockService.getPriceFromDate(this.stockDto, DATETIME);

        assertEquals(this.stockPrice.getPrice().valueToString(), creditsFound.valueToString());
    }

}