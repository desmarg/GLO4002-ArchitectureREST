package trading.persistence;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import trading.application.JerseyClient;
import trading.domain.datetime.DateTime;
import trading.external.response.market.MarketDTO;
import trading.external.response.market.MarketNotFoundException;
import trading.persistence.MarketAPIRepository;

import java.time.Instant;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MarketAPIRepositoryTest {
    private final String ZERO_TIMEZONE = "UTC+00:00";
    private final String VALID_TIMEZONE = "UTC+02:00";
    private final DateTime OPENED_DATETIME = new DateTime(Instant.parse("2018-11-19T10:00:00Z"));
    private final DateTime CLOSED_DATETIME = new DateTime(Instant.parse("2018-11-19T18:00:00Z"));
    private final DateTime OPENING_DATETIME = new DateTime(Instant.parse("2018-11-19T06:00:00Z"));
    private final DateTime CLOSING_DATETIME = new DateTime(Instant.parse("2018-11-19T17:00:00Z"));
    private final DateTime OPENED_DATETIME_WEEKEND = new DateTime(Instant.parse("2018-11-18T10:00:00Z"));
    private final DateTime OPENED_DATETIME_IN_OTHER_TIMEZONE = new DateTime(Instant.parse("2018" +
            "-11-19T05:00:00Z"));
    private final DateTime CLOSED_DATETIME_IN_OTHER_TIMEZONE = new DateTime(Instant.parse("2018" +
            "-11-19T16:00:00Z"));
    private final String MORNING_HOURS = "6:00-12:00";
    private final String PM_HOURS = "13:00-17:00";
    private final String MARKET_SYMBOL = "NASDAQ";
    private MarketAPIRepository marketAPIRepository;
    @Mock
    private JerseyClient jerseyClient;

    @Before
    public void setup() {
        this.marketAPIRepository = new MarketAPIRepository(this.jerseyClient);
    }

    @Test
    public void givenOpenedDateTime_whenCheckingIfMarketOpened_thenReturnTrue() {
        ArrayList<String> hours = new ArrayList<>();
        hours.add(this.MORNING_HOURS);
        hours.add(this.PM_HOURS);
        MarketDTO marketDTO = new MarketDTO();
        marketDTO.openHours = hours;
        marketDTO.timezone = this.ZERO_TIMEZONE;
        when(this.jerseyClient.getRequest(any(), any())).thenReturn(marketDTO);
        assertTrue(this.marketAPIRepository.isMarketOpenAtHour(this.MARKET_SYMBOL, this.OPENED_DATETIME));
    }

    @Test
    public void givenOpenedTime_whenCheckingIfMarketOpenedDuringWeekend_thenReturnFalse() {
        ArrayList<String> hours = new ArrayList<>();
        hours.add(this.MORNING_HOURS);
        hours.add(this.PM_HOURS);
        MarketDTO marketDTO = new MarketDTO();
        marketDTO.openHours = hours;
        marketDTO.timezone = this.ZERO_TIMEZONE;
        when(this.jerseyClient.getRequest(any(), any())).thenReturn(marketDTO);
        assertFalse(this.marketAPIRepository.isMarketOpenAtHour(this.MARKET_SYMBOL, this.OPENED_DATETIME_WEEKEND));
    }

    @Test
    public void givenOpenedDateTime_whenCheckingIfMarketOpenedAtTheOpeningTime_thenReturnTrue() {
        ArrayList<String> hours = new ArrayList<>();
        hours.add(this.MORNING_HOURS);
        hours.add(this.PM_HOURS);
        MarketDTO marketDTO = new MarketDTO();
        marketDTO.openHours = hours;
        marketDTO.timezone = this.ZERO_TIMEZONE;
        when(this.jerseyClient.getRequest(any(), any())).thenReturn(marketDTO);
        assertTrue(this.marketAPIRepository.isMarketOpenAtHour(this.MARKET_SYMBOL, this.OPENING_DATETIME));
    }

    @Test
    public void givenOpenedDateTime_whenCheckingIfMarketClosedAtTheClosingTime_thenReturnFalse() {
        ArrayList<String> hours = new ArrayList<>();
        hours.add(this.MORNING_HOURS);
        hours.add(this.PM_HOURS);
        MarketDTO marketDTO = new MarketDTO();
        marketDTO.openHours = hours;
        marketDTO.timezone = this.ZERO_TIMEZONE;
        when(this.jerseyClient.getRequest(any(), any())).thenReturn(marketDTO);
        assertFalse(this.marketAPIRepository.isMarketOpenAtHour(this.MARKET_SYMBOL, this.CLOSING_DATETIME));
    }

    @Test
    public void givenClosedDateTime_whenCheckingIfMarketOpened_thenReturnTrue() {
        ArrayList<String> hours = new ArrayList<>();
        hours.add(this.MORNING_HOURS);
        hours.add(this.PM_HOURS);
        MarketDTO marketDTO = new MarketDTO();
        marketDTO.openHours = hours;
        marketDTO.timezone = this.ZERO_TIMEZONE;
        when(this.jerseyClient.getRequest(any(), any())).thenReturn(marketDTO);
        assertFalse(this.marketAPIRepository.isMarketOpenAtHour(this.MARKET_SYMBOL,
                this.CLOSED_DATETIME));
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
        assertTrue(this.marketAPIRepository.isMarketOpenAtHour(this.MARKET_SYMBOL,
                this.OPENED_DATETIME_IN_OTHER_TIMEZONE));
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
        assertFalse(this.marketAPIRepository.isMarketOpenAtHour(this.MARKET_SYMBOL,
                this.CLOSED_DATETIME_IN_OTHER_TIMEZONE));
    }

    @Test(expected = MarketNotFoundException.class)
    public void givenInvalidMarket_whenCheckingIfMarketOpened_thenThrowMarketNotFoundException() {
        ArrayList<String> hours = new ArrayList<>();
        hours.add(this.MORNING_HOURS);
        hours.add(this.PM_HOURS);
        MarketDTO marketDTO = new MarketDTO();
        marketDTO.openHours = hours;
        marketDTO.timezone = this.VALID_TIMEZONE;
        when(this.jerseyClient.getRequest(any(), any())).thenReturn(null);
        this.marketAPIRepository.isMarketOpenAtHour(this.MARKET_SYMBOL, this.CLOSED_DATETIME_IN_OTHER_TIMEZONE);
    }
}