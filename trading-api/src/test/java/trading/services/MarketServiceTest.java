package trading.services;

import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import trading.external.response.Market.MarketDto;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MarketServiceTest {
    private String validTimezone = "+13:08";
    private String openedTime = "12:00";
    private String closedTime = "22:00";
    private MarketService marketService;

    @Before
    public void setup() {
        this.marketService = new MarketService();
    }

//    @Test
//    public void givenHoursAndTimeZoneAndTime_whenCheckingIfMarketOpened_thenReturnTrue() {
//        ArrayList<Pair<LocalTime, LocalTime>> hours = new ArrayList<>();
//        hours.add(new Pair<>(LocalTime.parse("06:00"), LocalTime.parse("12:00")));
//        hours.add(new Pair<>(LocalTime.parse("13:00"), LocalTime.parse("17:00")));
//        assertTrue(this.marketService.isMarketOpen(hours, ZoneOffset.of(this.validTimezone), LocalTime.parse(this.openedTime)));
//    }
//
//    @Test
//    public void givenHoursAndTimeZoneAndTime_whenCheckingIfMarketClosed_thenReturnFalse() {
//        ArrayList<Pair<LocalTime, LocalTime>> hours = new ArrayList<>();
//        hours.add(new Pair<>(LocalTime.parse("06:00"), LocalTime.parse("12:00")));
//        hours.add(new Pair<>(LocalTime.parse("13:00"), LocalTime.parse("17:00")));
//        assertFalse(this.marketService.isMarketOpen(hours, ZoneOffset.of(this.validTimezone), LocalTime.parse(this.closedTime)));
//    }

    @Test
    public void givenStringHours_whenParsingHours_thenReturnLocalTimesHours() {
        ArrayList<String> hours = new ArrayList<>();
        hours.add("06:00-12:00");
        hours.add("13:00-17:00");

        MarketDto marketDto = new MarketDto();
        marketDto.openHours = hours;
        ArrayList<Pair<LocalTime, LocalTime>> parsedHours = this.marketService.parseMarketHours(marketDto);
        assertEquals(parsedHours.get(0).getKey(), LocalTime.parse("06:00"));
        assertEquals(parsedHours.get(0).getValue(), LocalTime.parse("12:00"));
        assertEquals(parsedHours.get(1).getKey(), LocalTime.parse("13:00"));
        assertEquals(parsedHours.get(1).getValue(), LocalTime.parse("17:00"));
    }
}