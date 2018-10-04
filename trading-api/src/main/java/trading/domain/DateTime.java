package trading.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(""
            + "[yyyy-MM-dd'T'HH:mm:ss'Z']"
            + "[yyyy-MM-dd'T'HH:mm:ss.SSS'Z']"
    );
    private LocalDateTime localDateTime;

    public DateTime(String date) {
        this.localDateTime = LocalDateTime.parse(date, this.formatter);
    }

    public boolean isSameDay(DateTime dateTime) {
        return this.localDateTime.toLocalDate().equals(dateTime.localDateTime.toLocalDate());
    }

    @Override
    public String toString() {
        return this.localDateTime.toString();
    }
}
