package trading.services;

import javafx.util.Pair;
import trading.application.JerseyClient;
import trading.external.response.Market.MarketClosedException;
import trading.external.response.Market.MarketDto;
import trading.external.response.Market.MarketNotFoundException;

import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MarketService {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    public void assertMarketOpenAtHour(String market) {

        LocalTime time = LocalTime.now();
        MarketDto marketDto = this.getMarketDto(market);
        ArrayList<Pair<LocalTime, LocalTime>> times = this.parseMarketHours(marketDto);
        ZoneOffset offset = ZoneOffset.of(marketDto.timezone.substring(3));

        for (Pair<LocalTime, LocalTime> timesPair : times) {
            OffsetTime beginOffsetTime = OffsetTime.of(timesPair.getKey(), offset);
            OffsetTime endOffsetTime = OffsetTime.of(timesPair.getValue(), offset);
            if (!(time.compareTo(beginOffsetTime.toLocalTime()) >= 0 && time.compareTo(endOffsetTime.toLocalTime()) <= 0)) {
                throw new MarketClosedException(market);
            }
        }
    }

    public MarketDto getMarketDto(String market) {
        String url = "/markets/" + market;
        MarketDto marketDto = JerseyClient.getInstance().getRequest(url, MarketDto.class);
        if (marketDto == null) {
            throw new MarketNotFoundException(market);
        }
        return marketDto;
    }

    public ArrayList<Pair<LocalTime, LocalTime>> parseMarketHours(MarketDto marketDto) {
        ArrayList<String> hours = marketDto.openHours;
        ArrayList<Pair<LocalTime, LocalTime>> times = new ArrayList<>();
        for (String hour : hours) {
            String[] splitTime = hour.split("-");
            LocalTime beginTime = LocalTime.parse("00000".substring(splitTime[0].length()) + splitTime[0], this.formatter);
            LocalTime endTime = LocalTime.parse("00000".substring(splitTime[1].length()) + splitTime[1], this.formatter);
            times.add(new Pair<>(beginTime, endTime));
        }
        return times;
    }

}
