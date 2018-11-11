package trading.domain.DateTime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;

public class DateTime {
    private final OffsetDateTime dateTime;

    public DateTime(String date) {
        if (date == null) {
            throw new MissingDateException();
        }
        TimeZone timeZone = TimeZone.getDefault();
        String instantString = date.concat(" 23:59:59.999");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            LocalDateTime.parse(instantString, dateTimeFormatter);
        } catch (Exception e) {
            throw new InvalidDateException();
        }
        LocalDateTime localDateTime = LocalDateTime.parse(instantString, dateTimeFormatter);
        ZonedDateTime zonedDateTime = localDateTime.atZone(timeZone.toZoneId());
        Instant instant = zonedDateTime.toInstant();
        if ((instant.truncatedTo(ChronoUnit.DAYS)).compareTo(Instant.now().truncatedTo(ChronoUnit.DAYS)) >= 0) {
            throw new InvalidDateException();
        }
        OffsetDateTime myDate = OffsetDateTime.ofInstant(instant, ZoneId.of("UTC"));
        this.dateTime = myDate;
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

    public Instant toInstantDate() {
        return this.dateTime.toInstant().truncatedTo((ChronoUnit.DAYS));
    }

    public OffsetDateTime getDateTime() {
        return dateTime;
    }
}
