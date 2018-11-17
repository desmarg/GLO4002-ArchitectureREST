package trading.services;

import trading.application.JerseyClient;
import trading.domain.datetime.DateTime;
import trading.external.response.market.MarketDTO;
import trading.external.response.market.MarketNotFoundException;

import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MarketService {
    private final JerseyClient jerseyClient;
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_TIME;

    public MarketService(JerseyClient jerseyClient) {
        this.jerseyClient = jerseyClient;
    }

    public boolean isMarketOpenAtHour(String market, DateTime currentDateTime) {
        OffsetTime transactionTime = currentDateTime.toOffsetDateTime().toOffsetTime();
        MarketDTO marketDto = this.getMarketDto(market);
        List<List<OffsetTime>> marketHours = this.parseMarketHours(marketDto);
        for (List<OffsetTime> openCloseOffsetTimes : marketHours) {
            if (transactionTime.compareTo(openCloseOffsetTimes.get(0)) >= 0
                    && transactionTime.compareTo(openCloseOffsetTimes.get(1)) <= 0) {
                return true;
            }
        }
        return false;
    }

    public MarketDTO getMarketDto(String market) {
        String url = "/markets/" + market;
        MarketDTO marketDto = this.jerseyClient.getRequest(url, MarketDTO.class);
        if (marketDto == null) {
            throw new MarketNotFoundException(market);
        }
        return marketDto;
    }

    private List parseMarketHours(MarketDTO marketDto) {
        List<String> hours = marketDto.openHours;
        ZoneOffset zoneOffset = ZoneOffset.of(marketDto.timezone.substring(3));
        List marketHours = new ArrayList<ArrayList<DateTime>>();
        for (String hour : hours) {
            List openCloseOffsetTimes = new ArrayList();
            String[] splitTime = hour.split("-");
            OffsetTime beginTime =
                    OffsetTime.of(LocalTime.parse(this.conditionalLeftPad(splitTime[0]),
                            this.formatter), zoneOffset);
            openCloseOffsetTimes.add(beginTime);
            OffsetTime endTime =
                    OffsetTime.of(LocalTime.parse(this.conditionalLeftPad(splitTime[1]),
                            this.formatter), zoneOffset);
            openCloseOffsetTimes.add(endTime);
            marketHours.add(openCloseOffsetTimes);
        }
        return marketHours;
    }

    private String conditionalLeftPad(String str) {
        if (str.length() == 4) {
            str = "0" + str;
        }
        return str;
    }
}
