package trading.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import trading.application.JerseyClient;
import trading.domain.DateTime.DateTime;
import trading.domain.Stock;
import trading.domain.transaction.StockNotFoundException;
import trading.external.response.StockApiDTO;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class StockServiceTest {
    private static final String A_MARKET = "HELLO";
    private static final String A_SYMBOL = "WORLD";
    private static final String A_URL = "A";
    @Mock
    StockApiDTO stockApiDTO;
    private StockService stockService;
    private Stock stock;
    private DateTime dateTime;
    @Mock
    private JerseyClient jerseyClient;

    @Before
    public void setup() {
        this.stockService = new StockService(this.jerseyClient);
    }

    @Test(expected = StockNotFoundException.class)
    public void givenInvalidStockAndDateTime_whenRetrieveStockPrice_thenStockNotFoundException() {
        this.stock = new Stock(A_MARKET, A_SYMBOL);
        when(this.jerseyClient.getRequest(any(), any())).thenReturn(this.stockApiDTO);
        this.stockService.retrieveStockPrice(this.stock, this.dateTime);
    }

    @Test
    public void givenValidDateTime_whenGetPriceFromDateTime_thenReturnCredits() {
    }
}
