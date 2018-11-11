package trading.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import trading.application.JerseyClient;
import trading.domain.Credits.Credits;
import trading.domain.DateTime.DateTime;
import trading.domain.DateTime.InvalidDateException;
import trading.domain.Stock;
import trading.domain.transaction.StockNotFoundException;
import trading.external.response.StockApiDTO;
import trading.external.response.StockPriceResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StockServiceTest {
    private static final String A_MARKET = "HELLO";
    private static final String A_SYMBOL = "WORLD";
    private static final String A_TYPE = "TYPE";
    private static final int CREDIT_VALUE = 10;
    private static final long NORMAL_ID = 1L;
    private StockService stockService;
    private final DateTime VALID_DATETIME = new DateTime(OffsetDateTime.of(LocalDateTime.parse("2000-08-04T05:00:00"), ZoneOffset.of("+00:00")));
    private final DateTime INVALID_DATETIME = new DateTime(OffsetDateTime.of(LocalDateTime.parse("2001-08-04T05:00:00"), ZoneOffset.of("+00:00")));
    @Mock
    private JerseyClient jerseyClient;

    @Before
    public void setup() {
        this.stockService = new StockService(this.jerseyClient);
    }

    @Test(expected = StockNotFoundException.class)
    public void givenInvalidStockAndDateTime_whenRetrieveStockPrice_thenStockNotFoundException() {
        Stock stock = new Stock(A_MARKET, A_SYMBOL);
        when(this.jerseyClient.getRequest(any(), any())).thenReturn(null);
        this.stockService.retrieveStockPrice(stock, this.VALID_DATETIME);
    }

    @Test
    public void givenValidDateTime_whenGetPriceFromDateTime_thenReturnCredits() {
        Stock stock = new Stock(A_MARKET, A_SYMBOL);
        StockApiDTO stockApiDTO = new StockApiDTO();
        stockApiDTO.id = NORMAL_ID;
        stockApiDTO.market = A_MARKET;
        stockApiDTO.symbol = A_SYMBOL;
        stockApiDTO.type = A_TYPE;
        stockApiDTO.prices = new ArrayList<>();
        StockPriceResponseDTO stockPriceResponseDTO = new StockPriceResponseDTO();
        stockPriceResponseDTO.date = this.VALID_DATETIME.toInstantDate();
        stockPriceResponseDTO.price = new BigDecimal(CREDIT_VALUE);
        stockApiDTO.prices.add(stockPriceResponseDTO);
        when(this.jerseyClient.getRequest(any(), any())).thenReturn(stockApiDTO);
        Credits returning = this.stockService.retrieveStockPrice(stock, this.VALID_DATETIME);
        assertEquals(returning, new Credits(CREDIT_VALUE));
    }

    @Test(expected = InvalidDateException.class)
    public void givenInValidDateTime_whenGetPriceFromDateTime_thenInvalidDateException() {
        Stock stock = new Stock(A_MARKET, A_SYMBOL);
        StockApiDTO stockApiDTO = new StockApiDTO();
        stockApiDTO.id = NORMAL_ID;
        stockApiDTO.market = A_MARKET;
        stockApiDTO.symbol = A_SYMBOL;
        stockApiDTO.type = A_TYPE;
        stockApiDTO.prices = new ArrayList<>();
        StockPriceResponseDTO stockPriceResponseDTO = new StockPriceResponseDTO();
        stockPriceResponseDTO.date = this.VALID_DATETIME.toInstantDate();
        stockPriceResponseDTO.price = new BigDecimal(CREDIT_VALUE);
        stockApiDTO.prices.add(stockPriceResponseDTO);
        when(this.jerseyClient.getRequest(any(), any())).thenReturn(stockApiDTO);
        this.stockService.retrieveStockPrice(stock, this.INVALID_DATETIME);
    }
}
