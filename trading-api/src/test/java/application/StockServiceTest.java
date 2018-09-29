package application;

import domain.Credits;
import domain.DateTime;
import domain.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.*;

@RunWith(MockitoJUnitRunner.class)
public class StockServiceTest {

    @Test
    public void givenValidStock_whenGettingStockPriceWithValidDate_shouldReturnPrice() {
        Stock stock = new Stock("NASDAQ", "MSFT");
        Credits price = StockService.getStockPrice(stock, new DateTime("2018-08-21T15:23:20.142Z"));
        assertEquals(Credits.fromFloat(157.54F), price);
    }
}