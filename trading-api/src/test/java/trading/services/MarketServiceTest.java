package trading.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import trading.application.JerseyClient;
import trading.domain.datetime.DateTime;
import trading.external.response.Market.MarketDTO;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MarketServiceTest {
    private final String ZERO_TIMEZONE = "UTC+00:00";
    private final String VALID_TIMEZONE = "UTC+02:00";
    private final DateTime OPENED_DATETIME = new DateTime(OffsetDateTime.of(LocalDateTime.parse("2018-08-04T10:00:00"), ZoneOffset.of("+00:00")));
    private final DateTime CLOSED_DATETIME = new DateTime(OffsetDateTime.of(LocalDateTime.parse("2018-08-04T18:00:00"), ZoneOffset.of("+00:00")));
    private final DateTime OPENED_DATETIME_IN_OTHER_TIMEZONE = new DateTime(OffsetDateTime.of(LocalDateTime.parse("2018-08-04T05:00:00"), ZoneOffset.of("+00:00")));
    private final DateTime CLOSED_DATETIME_IN_OTHER_TIMEZONE = new DateTime(OffsetDateTime.of(LocalDateTime.parse("2018-08-04T16:00:00"), ZoneOffset.of("+00:00")));
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
    public void givenOpenedDateTimeAndEmptyTimeZone_whenCheckingIfMarketOpened_thenReturnTrue() {
        ArrayList<String> hours = new ArrayList<>();
        hours.add(this.MORNING_HOURS);
        hours.add(this.PM_HOURS);
        MarketDTO marketDTO = new MarketDTO();
        marketDTO.openHours = hours;
        marketDTO.timezone = this.ZERO_TIMEZONE;
        when(this.jerseyClient.getRequest(any(), any())).thenReturn(marketDTO);
        assertTrue(this.marketService.isMarketOpenAtHour(this.MARKET_SYMBOL, this.OPENED_DATETIME));
    }

    @Test
    public void givenClosedDateTimeAndEmptyTimeZone_whenCheckingIfMarketOpened_thenReturnTrue() {
        ArrayList<String> hours = new ArrayList<>();
        hours.add(this.MORNING_HOURS);
        hours.add(this.PM_HOURS);
        MarketDTO marketDTO = new MarketDTO();
        marketDTO.openHours = hours;
        marketDTO.timezone = this.ZERO_TIMEZONE;
        when(this.jerseyClient.getRequest(any(), any())).thenReturn(marketDTO);
        assertFalse(this.marketService.isMarketOpenAtHour(this.MARKET_SYMBOL, this.CLOSED_DATETIME));
    }

    @Test
    public void givenOpenedDateTimeAndValidTimeZone_whenCheckingIfMarketOpened_thenReturnTrue() {
        ArrayList<String> hours = new ArrayList<>();
        hours.add(this.MORNING_HOURS);
        hours.add(this.PM_HOURS);
        MarketDTO marketDTO = new MarketDTO();
        marketDTO.openHours = hours;
        marketDTO.timezone = this.VALID_TIMEZONE;
        when(this.jerseyClient.getRequest(any(), any())).thenReturn(marketDTO);
        assertTrue(this.marketService.isMarketOpenAtHour(this.MARKET_SYMBOL, this.OPENED_DATETIME_IN_OTHER_TIMEZONE));
    }

    @Test
    public void givenClosedDateTimeAndValidTimeZone_whenCheckingIfMarketOpened_thenReturnTrue() {
        ArrayList<String> hours = new ArrayList<>();
        hours.add(this.MORNING_HOURS);
        hours.add(this.PM_HOURS);
        MarketDTO marketDTO = new MarketDTO();
        marketDTO.openHours = hours;
        marketDTO.timezone = this.VALID_TIMEZONE;
        when(this.jerseyClient.getRequest(any(), any())).thenReturn(marketDTO);
        assertFalse(this.marketService.isMarketOpenAtHour(this.MARKET_SYMBOL, this.CLOSED_DATETIME_IN_OTHER_TIMEZONE));
    }
}