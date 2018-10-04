package domain.stock;

import application.stock.StockDto;
import application.stock.StockPrice;
import application.stock.StockService;
import domain.Credits;
import domain.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class StockServiceTest {
    private static String DATE = "2015-01-01T05:00:00.000Z";
    private static DateTime DATETIME = new DateTime(DATE);
    private static Double PRICE = 10d;

    private StockService stockService;
    private StockDto stockDto;
    private StockPrice stockPrice;
    private ArrayList<StockPrice> stockPrices;

    @Before
    public void setup() {
        this.stockService = new StockService();

        this.stockPrice = new StockPrice();
        this.stockPrice.setDate(DATE);
        this.stockPrice.setPrice(PRICE);

        this.stockPrices = new ArrayList<>();
        this.stockPrices.add(this.stockPrice);

        this.stockDto = new StockDto();
        this.stockDto.setPrices(this.stockPrices);

    }

    @Test
    public void givenValidDateTime_whenGetPriceFromDate_thenReturnCredits() {
        Credits creditsFound = this.stockService.getPriceFromDate(this.stockDto, DATETIME);

        assertEquals(this.stockPrice.getPrice().valueToString(), creditsFound.valueToString());
    }

}