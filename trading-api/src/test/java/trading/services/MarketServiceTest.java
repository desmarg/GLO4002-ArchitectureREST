package trading.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import trading.application.JerseyClient;
import trading.external.response.Market.MarketDTO;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MarketServiceTest {
    private final String VALID_TIMEZONE = "UTC-01:08";
    private final String OPENED_TIME = "12:00";
    private final String CLOSED_TIME = "22:00";
    private final String MORNING_HOURS = "6:00-12:00";
    private final String PM_HOURS = "13:00-17:00";
    private final String MARKET_SYMBOL = "NASDAQ";
    private MarketService marketService;
    @Mock
    private JerseyClient jerseyClient;

    @Before
    public void setup() {

        this.marketService = new MarketService(this.jerseyClient);
    }

    @Test
    public void assertTrues() {
        assertTrue(true);
    }

    @Test
    public void givenHoursAndTimeZoneAndTime_whenCheckingIfMarketOpened_thenReturnTrue() {
        ArrayList<String> hours = new ArrayList<>();
        hours.add(this.MORNING_HOURS);
        hours.add(this.PM_HOURS);
        MarketDTO marketDTO = new MarketDTO();
        marketDTO.openHours = hours;
        marketDTO.timezone = this.VALID_TIMEZONE;
        when(this.jerseyClient.getRequest(any(), any())).thenReturn(marketDTO);
        this.marketService.isMarketOpenAtHour(this.MARKET_SYMBOL, LocalTime.parse(this.OPENED_TIME));
        assertTrue(this.marketService.isMarketOpenAtHour(this.MARKET_SYMBOL, LocalTime.parse(this.OPENED_TIME)));
    }

    @Test
    public void givenHoursAndTimeZoneAndTime_whenCheckingIfMarketClosed_thenReturnFalse() {
        ArrayList<String> hours = new ArrayList<>();
        hours.add(this.MORNING_HOURS);
        hours.add(this.PM_HOURS);
        MarketDTO marketDTO = new MarketDTO();
        marketDTO.openHours = hours;
        marketDTO.timezone = this.VALID_TIMEZONE;
        when(this.jerseyClient.getRequest(any(), any())).thenReturn(marketDTO);
        assertFalse(this.marketService.isMarketOpenAtHour(this.MARKET_SYMBOL, LocalTime.parse(this.CLOSED_TIME)));
    }
}