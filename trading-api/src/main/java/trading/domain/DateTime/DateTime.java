package trading.domain.DateTime;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class DateTime {
    private final OffsetDateTime dateTime;

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

    public Instant toInstant() {
        return this.dateTime.toInstant();
    }

    public Instant getInstantDate() {
        return this.toInstant().truncatedTo((ChronoUnit.DAYS));
    }
}
