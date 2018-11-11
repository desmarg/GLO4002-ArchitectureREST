package trading.domain.DateTime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;

public class DateTime {
    private final OffsetDateTime dateTime;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public DateTime(String date) {
        if (date == null) {
            throw new MissingDateException();
        }
        TimeZone timeZone = TimeZone.getDefault();
        String dateInstant = date.concat(" 23:59:59.999");
        this.validateDate(dateInstant);
        LocalDateTime localDateTime = LocalDateTime.parse(dateInstant, this.dateTimeFormatter);
        ZonedDateTime zonedDateTime = localDateTime.atZone(timeZone.toZoneId());
        Instant instant = zonedDateTime.toInstant();
        this.isDateBeforeNow(instant);
        OffsetDateTime myDate = OffsetDateTime.ofInstant(instant, ZoneId.of("UTC"));
        this.dateTime = myDate;
    }

    public DateTime(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public static DateTime fromInstant(Instant instant) {
        return new DateTime(OffsetDateTime.ofInstant(instant, ZoneId.of("UTC")));
    }

    private void isDateBeforeNow(Instant instant) {
        if ((instant.truncatedTo(ChronoUnit.DAYS)).compareTo(Instant.now().truncatedTo(ChronoUnit.DAYS)) >= 0) {
            throw new InvalidDateException();
        }
    }

    private void validateDate(String dateInstant) {
        try {
            LocalDateTime.parse(dateInstant, this.dateTimeFormatter);
        } catch (Exception e) {
            throw new InvalidDateException();
        }
    }

    public Instant toInstant() {
        return this.dateTime.toInstant();
    }

    public Instant toInstantDate() {
        return this.dateTime.toInstant().truncatedTo((ChronoUnit.DAYS));
    }

    public OffsetDateTime getDateTime() {
        return this.dateTime;
    }
}
