package application.market;

import application.JerseyClient;
import exception.InvalidTimezoneException;
import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.cglib.core.Local;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MarketServiceTest {
    private String validTimezone = "+13:08";
    private String openedTime = "12:00";
    private String closedTime = "22:00";
    private MarketService marketService;

    @Before
    public void setup(){
        marketService = new MarketService();
    }

    @Test
    public void givenHoursAndTimeZoneAndTime_whenCheckingIfMarketOpened_thenReturnTrue(){
        ArrayList<Pair<LocalTime, LocalTime>> hours = new ArrayList<>();
        hours.add(new Pair<>(LocalTime.parse("06:00"),LocalTime.parse("12:00")));
        hours.add(new Pair<>(LocalTime.parse("13:00"),LocalTime.parse("17:00")));
        assertTrue(marketService.validateMarketOpenAtHour(hours, ZoneOffset.of(validTimezone),LocalTime.parse(openedTime)));
    }

    @Test
    public void givenHoursAndTimeZoneAndTime_whenCheckingIfMarketClosed_thenReturnFalse(){
        ArrayList<Pair<LocalTime, LocalTime>> hours = new ArrayList<>();
        hours.add(new Pair<>(LocalTime.parse("06:00"),LocalTime.parse("12:00")));
        hours.add(new Pair<>(LocalTime.parse("13:00"),LocalTime.parse("17:00")));
        assertFalse(marketService.validateMarketOpenAtHour(hours, ZoneOffset.of(validTimezone),LocalTime.parse(closedTime)));
    }

    @Test
    public void givenStringHours_whenParsingHours_thenReturnLocalTimesHours(){
        ArrayList<String> hours = new ArrayList<>();
        hours.add("06:00-12:00");
        hours.add("13:00-17:00");

        MarketDto marketDto = new MarketDto();
        marketDto.openHours = hours;
        ArrayList<Pair<LocalTime, LocalTime>> parsedHours = marketService.parseMarketHours(marketDto);
        assertEquals(parsedHours.get(0).getKey(), LocalTime.parse("06:00"));
        assertEquals(parsedHours.get(0).getValue(), LocalTime.parse("12:00"));
        assertEquals(parsedHours.get(1).getKey(), LocalTime.parse("13:00"));
        assertEquals(parsedHours.get(1).getValue(), LocalTime.parse("17:00"));
    }
}