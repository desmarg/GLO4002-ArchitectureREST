package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {

    private final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private final String DATE_FORMAT2 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(this.DATE_FORMAT);
    private final DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern(this.DATE_FORMAT2);

    private LocalDateTime localDateTime;

    public DateTime(String date) {
        try {
            this.localDateTime = LocalDateTime.parse(date, this.dateTimeFormatter);
        } catch (Exception e) {
            this.localDateTime = LocalDateTime.parse(date, this.dateTimeFormatter2);
        }
    }

    public boolean isSameDay(DateTime dateTime) {
        return this.localDateTime.toLocalDate().equals(dateTime.localDateTime.toLocalDate());
    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (obj instanceof DateTime) {
//            return ((DateTime) obj).localDateTime.equals(this.localDateTime);
//        }
//        return false;
//    }
}
