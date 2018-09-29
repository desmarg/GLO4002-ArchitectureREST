package application;

import domain.Credits;
import domain.DateTime;
import domain.Stock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.*;

@RunWith(MockitoJUnitRunner.class)
public class StockServiceTest {
    private StockService stockService;

    @Before
    public void setUp() {
        stockService = new StockService();
    }

    @Test
    public void givenValidStock_whenGettingStockPriceWithValidDate_shouldReturnPrice() throws Exception {
        Stock stock = new Stock("NASDAQ", "MSFT");
        Credits price = stockService.getStockPrice(stock, new DateTime("2015-01-01T05:00:00Z"));
        assertEquals(Credits.fromFloat(157.54F), price);
    }
}