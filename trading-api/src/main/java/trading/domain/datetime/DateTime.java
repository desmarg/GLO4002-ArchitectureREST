package trading.domain.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;

public class DateTime {

    private final Instant instant;

    public DateTime(Instant instant) {
        this.instant = instant;
    }

    public Instant toInstantTruncatedToDay() {
        return this.instant.truncatedTo((ChronoUnit.DAYS));
    }

    public OffsetDateTime toOffsetDateTime() {
        return OffsetDateTime.ofInstant(this.instant, ZoneId.of("UTC"));
    }

    public Instant toInstant() {
        return this.instant;
    }
}
