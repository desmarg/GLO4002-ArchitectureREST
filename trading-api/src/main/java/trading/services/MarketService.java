package trading.services;

import trading.application.JerseyClient;
import trading.external.response.Market.MarketDTO;
import trading.external.response.Market.MarketNotFoundException;

import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketService {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    private final JerseyClient jerseyClient;

    public MarketService(JerseyClient jerseyClient) {
        this.jerseyClient = jerseyClient;
    }

    public boolean isMarketOpen(String market) {

        LocalTime time = LocalTime.now();
        MarketDTO marketDto = this.getMarketDto(market);
        Map<LocalTime, LocalTime> times = this.parseMarketHours(marketDto);
        ZoneOffset offset = ZoneOffset.of(marketDto.timezone.substring(3));

        for (Map.Entry<LocalTime, LocalTime> OpenCloseTimes : times.entrySet()) {
            OffsetTime beginOffsetTime = OffsetTime.of(OpenCloseTimes.getKey(), offset);
            OffsetTime endOffsetTime = OffsetTime.of(OpenCloseTimes.getValue(), offset);
            if (!(time.compareTo(beginOffsetTime.toLocalTime()) >= 0 && time.compareTo(endOffsetTime.toLocalTime()) <= 0)) {
                return false;
            }
        }
        return true;
    }

    public MarketDTO getMarketDto(String market) {
        String url = "/markets/" + market;
        MarketDTO marketDto = this.jerseyClient.getRequest(url, MarketDTO.class);
        if (marketDto == null) {
            throw new MarketNotFoundException(market);
        }
        return marketDto;
    }

    private Map<LocalTime, LocalTime> parseMarketHours(MarketDTO marketDto) {
        List<String> hours = marketDto.openHours;
        Map pairMap = new HashMap<LocalTime, LocalTime>();
        for (String hour : hours) {
            String[] splitTime = hour.split("-");
            LocalTime beginTime = LocalTime.parse("00000".substring(splitTime[0].length()) + splitTime[0], this.formatter);
            LocalTime endTime = LocalTime.parse("00000".substring(splitTime[1].length()) + splitTime[1], this.formatter);
            pairMap.put(beginTime, endTime);

        }
        return pairMap;
    }

}
