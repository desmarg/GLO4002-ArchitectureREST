package trading.domain;

import com.fasterxml.jackson.annotation.JsonValue;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;

public class DateTime {
    private Instant instant;

    public DateTime(String text) {
        this.instant = Instant.parse(text);
    }

    public DateTime(String text, int hour, int minutes, int seconds){
        LocalDate localDate = LocalDate.parse(text);
        LocalTime localTime = LocalTime.of(hour, minutes, seconds);
        this.instant = LocalDateTime.of(localDate, localTime).toInstant(ZoneOffset.UTC);
    }

    @JsonValue
    public String toString() {
        return this.instant.toString();
    }

    public boolean isEquals(DateTime comparedDateTime) {
        return this.instant.equals(comparedDateTime.asInstant());
    }

    public boolean isSameDay(DateTime comparedDateTime) {
        return this.truncatedToDays().equals(comparedDateTime.truncatedToDays());
    }

    public Instant truncatedToDays() {
        return this.instant.truncatedTo(ChronoUnit.DAYS);
    }

    public Instant asInstant() {
        return this.instant;
    }

}
