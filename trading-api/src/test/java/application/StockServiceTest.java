package application;

import domain.Stock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;


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
        double price = stockService.getStockPrice(stock, "2015-01-01T05:00:00Z");
        assertEquals(157.54, price);
    }
}