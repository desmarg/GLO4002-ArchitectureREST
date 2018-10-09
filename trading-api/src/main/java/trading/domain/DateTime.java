package trading.domain;

import com.fasterxml.jackson.annotation.JsonValue;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DateTime {
    private Instant instant;

    public DateTime(String text) {
        this.instant = Instant.parse(text);
    }

    @JsonValue
    public String toString() {
        return this.instant.toString();
    }

    public Instant truncatedToDays() {
        return this.instant.truncatedTo(ChronoUnit.DAYS);
    }

    public Instant asInstant() {
        return this.instant;
    }
}
