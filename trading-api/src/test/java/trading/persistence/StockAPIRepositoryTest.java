package trading.persistence;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import trading.api.request.StockDTO;
import trading.application.JerseyClient;
import trading.domain.Credits;
import trading.domain.Currency;
import trading.domain.datetime.DateTime;
import trading.domain.datetime.InvalidDateException;
import trading.domain.transaction.StockNotFoundException;
import trading.external.response.StockApiDTO;
import trading.external.response.StockPriceResponseDTO;
import trading.persistence.StockAPIRepository;

import java.time.Instant;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StockAPIRepositoryTest {
    private static final String A_MARKET = "HELLO";
    private static final String A_SYMBOL = "WORLD";
    private static final String A_TYPE = "TYPE";
    private static final Credits CREDITS = Credits.fromInteger(10, Currency.CAD);
    private static final Long NORMAL_ID = 1L;
    private final DateTime VALID_DATETIME = new DateTime(Instant.parse("2000-08-04T05:00:00Z"));
    private final DateTime INVALID_DATETIME = new DateTime(Instant.parse("2001-08-04T05:00:00Z"));
    private StockAPIRepository stockAPIRepository;
    private StockDTO stock;
    private StockApiDTO stockApiDTO;
    @Mock
    private JerseyClient jerseyClient;

    @Before
    public void setup() {
        this.stockAPIRepository = new StockAPIRepository(this.jerseyClient);
        this.stock = new StockDTO();
        this.stock.market = A_MARKET;
        this.stock.symbol = A_SYMBOL;
        this.stockApiDTO = new StockApiDTO();
        this.stockApiDTO.id = NORMAL_ID;
        this.stockApiDTO.market = A_MARKET;
        this.stockApiDTO.symbol = A_SYMBOL;
        this.stockApiDTO.type = A_TYPE;
        this.stockApiDTO.prices = new ArrayList<>();
        StockPriceResponseDTO stockPriceResponseDTO = new StockPriceResponseDTO();
        stockPriceResponseDTO.date = this.VALID_DATETIME.toInstantTruncatedToDay();
        stockPriceResponseDTO.price = CREDITS.getAmount();
        this.stockApiDTO.prices.add(stockPriceResponseDTO);
        when(this.jerseyClient.getRequest(any(), any())).thenReturn(this.stockApiDTO);
    }

    @Test(expected = StockNotFoundException.class)
    public void givenInvalidStockAndDateTime_whenRetrieveStockPrice_thenStockNotFoundException() {
        when(this.jerseyClient.getRequest(any(), any())).thenReturn(null);

        this.stockAPIRepository.retrieveStockPrice(this.stock, this.VALID_DATETIME, false);
    }

    @Test
    public void givenValidDateTime_whenRetrieveStockPrice_thenReturnCredits() {
        Credits returning = this.stockAPIRepository.retrieveStockPrice(this.stock, this.VALID_DATETIME, false);

        assertEquals(returning, CREDITS);
    }

    @Test(expected = InvalidDateException.class)
    public void givenInValidDateTime_whenRetrieveStockPrice_thenInvalidDateException() {
        when(this.jerseyClient.getRequest(any(), any())).thenReturn(this.stockApiDTO);

        this.stockAPIRepository.retrieveStockPrice(this.stock, this.INVALID_DATETIME, false);
    }
}
