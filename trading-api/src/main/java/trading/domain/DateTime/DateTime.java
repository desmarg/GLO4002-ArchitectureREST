package trading.domain.DateTime;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

public class DateTime {
    private OffsetDateTime dateTime;

    public DateTime(String date) {
        Instant instant = Instant.parse(date);
        this.dateTime = OffsetDateTime.ofInstant(instant, ZoneId.of("UTC"));
    }

    public DateTime(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public static DateTime fromInstant(Instant instant) {
        return new DateTime(OffsetDateTime.ofInstant(instant, ZoneId.of("UTC")));
    }

    public Integer getDayOfYear() {
        return this.dateTime.getDayOfYear();
    }

    public Instant toInstant() {
        return this.dateTime.toInstant();
    }
}
