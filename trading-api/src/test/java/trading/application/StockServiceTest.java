//package application;
//
//import trading.domain.Credits;
//import trading.domain.DateTime;
//import trading.domain.stock.Stock;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import static junit.framework.TestCase.*;
//
//@RunWith(MockitoJUnitRunner.class)
//public class StockServiceTest {
//
//    @Test
//    public void givenValidStock_whenGettingStockPriceWithValidDate_shouldReturnPrice() {
//        Stock stock = new Stock("NASDAQ", "MSFT");
//        Credits price = StockService.getStockPrice(stock, new DateTime("2015-01-01T05:00:00Z"));
//        assertEquals(Credits.fromDouble(157.54F), price);
//    }
//}