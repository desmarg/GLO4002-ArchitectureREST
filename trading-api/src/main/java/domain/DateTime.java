package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {

    private final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(this.DATE_FORMAT);
    private LocalDateTime localDateTime;

    public DateTime(String date) {
        this.localDateTime = LocalDateTime.parse(date, this.dateTimeFormatter);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DateTime) {
            return ((DateTime) obj).localDateTime.equals(this.localDateTime);
        }
        return false;
    }
}
