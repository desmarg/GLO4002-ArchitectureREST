package domain.stock;

import application.stock.PriceDTO;
import application.stock.StockDTO;
import domain.Credits;
import domain.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class StockInfoTest {
    private static String DATE = "2015-01-01T05:00:00.000Z";
    private static DateTime DATETIME = new DateTime(DATE);
    private static Float PRICE = 10f;

    private StockDTO stockInfo;
    private PriceDTO priceInfo;
    private ArrayList<PriceDTO> priceInfos;

    @Before
    public void setup() {
        this.priceInfo = new PriceDTO();
        this.priceInfo.setDate(DATE);
        this.priceInfo.setPrice(PRICE);

        this.priceInfos = new ArrayList<>();
        this.priceInfos.add(this.priceInfo);

        this.stockInfo = new StockDTO();
        this.stockInfo.setPrices(this.priceInfos);

    }

    @Test
    public void givenValidDateTime_whenGetPriceFromDate_thenReturnCredits() {
        Credits creditsFound = this.stockInfo.getPriceFromDate(DATETIME);

        assertEquals(this.priceInfo.getPrice().valueToString(), creditsFound.valueToString());
    }

}