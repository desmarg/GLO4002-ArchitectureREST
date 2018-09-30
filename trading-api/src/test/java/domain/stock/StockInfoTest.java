package domain.stock;

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

    private StockInfo stockInfo;
    private PriceInfo priceInfo;
    private ArrayList<PriceInfo> priceInfos;

    @Before
    public void setup() {
        this.priceInfo = new PriceInfo();
        this.priceInfo.setDate(DATE);
        this.priceInfo.setPrice(PRICE);

        this.priceInfos = new ArrayList<>();
        this.priceInfos.add(this.priceInfo);

        this.stockInfo = new StockInfo();
        this.stockInfo.setPrices(this.priceInfos);

    }

    @Test
    public void givenValidDateTime_whenGetPriceFromDate_thenReturnCredits() {
        Credits creditsFound = this.stockInfo.getPriceFromDate(DATETIME);

        assertEquals(this.priceInfo.getPrice().valueToString(), creditsFound.valueToString());
    }

}