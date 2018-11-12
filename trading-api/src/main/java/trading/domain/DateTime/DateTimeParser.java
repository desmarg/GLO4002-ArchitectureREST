package trading.domain.DateTime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;

public class DateTimeParser {

    public static DateTime createFromReportDate(String date) {
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
        return new DateTime(instant);
    }
}
