package trading.domain.datetime;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

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
