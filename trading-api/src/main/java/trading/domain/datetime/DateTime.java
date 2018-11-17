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

    public DateTime(OffsetDateTime offsetDateTime) {
        this.instant = offsetDateTime.toInstant();
    }

    public DateTime(String date) {
        this.instant = this.stringToInstantParser(date);
    }

    public static DateTime fromInstant(Instant instant) {
        return new DateTime(OffsetDateTime.ofInstant(instant, ZoneId.of("UTC")));
    }

    public Instant toDate() {
        return this.instant.truncatedTo((ChronoUnit.DAYS));
    }

    public OffsetDateTime toOffsetDateTime() {
        return OffsetDateTime.ofInstant(this.instant, ZoneId.of("UTC"));
    }

    public Instant toInstant() {
        return this.instant;
    }

    private Instant stringToInstantParser(String date) {
        if (date == null) {
            throw new MissingDateException();
        }
        TimeZone timeZone = TimeZone.getDefault();
        String instantString = date.concat(" 23:59:59.999");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime localDateTime = this.parseDate(instantString, dateTimeFormatter);
        ZonedDateTime zonedDateTime = localDateTime.atZone(timeZone.toZoneId());
        Instant instant = zonedDateTime.toInstant();
        if ((instant.truncatedTo(ChronoUnit.DAYS)).compareTo(Instant.now().truncatedTo(ChronoUnit.DAYS)) >= 0) {
            throw new InvalidDateException();
        }
        return instant;
    }

    private LocalDateTime parseDate(String instantString, DateTimeFormatter dateTimeFormatter) {
        try {
            return LocalDateTime.parse(instantString, dateTimeFormatter);
        } catch (Exception e) {
            throw new InvalidDateException();
        }
    }

}
