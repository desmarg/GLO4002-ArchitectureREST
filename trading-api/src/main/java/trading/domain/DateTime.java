package trading.domain;

import trading.exception.InvalidDateException;
import trading.exception.InvalidTransactionDateException;
import trading.exception.MissingDateException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    public DateTime(String date, int hour, int minutes, int seconds){
        if(date == null){
            throw new MissingDateException();
        }
        try {
            LocalDate localDate = LocalDate.parse(date);
            LocalTime localTime = LocalTime.of(hour, minutes, seconds);
            this.localDateTime = LocalDateTime.of(localDate, localTime);
        } catch (Exception e){
            throw new InvalidDateException(date);
        }
    }

    public boolean isSameDay(DateTime dateTime) {
        return this.localDateTime.toLocalDate().equals(dateTime.localDateTime.toLocalDate());
    }

    @Override
    public String toString() {
        return this.localDateTime.toString();
    }
}
