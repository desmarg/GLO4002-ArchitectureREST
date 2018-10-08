package application.market;

import application.JerseyClient;
import exception.MarketNotFoundException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MarketService {
    private static int maxHourOfDay = 23;
    private static int maxMinuteOfDay = 59;
    private static MarketService INSTANCE = null;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    public static MarketService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MarketService();
        }

        return INSTANCE;
    }

    public MarketDto getMarketDto(String market) {
        String url = "/markets/" + market;
        MarketDto marketDto = JerseyClient.getInstance().getRequest(url, MarketDto.class);
        if (marketDto == null) {
            throw new MarketNotFoundException(market);
        }
        return marketDto;
    }

    public boolean getMarketOpenAtHour(String market, LocalTime time) {
        String url = "/markets/" + market;
        MarketDto marketDto = getMarketDto(market);
        ArrayList<String> hours = marketDto.getOpenHours();
        boolean opened = false;
        LocalTime timeModifier = getTimeModifierFromTimeZone(marketDto.getTimezone());
        for (String times : hours) {
            String[] splitTime = times.split("-");
            LocalTime beginTime = LocalTime.parse("00000".substring(splitTime[0].length()) + splitTime[0], this.formatter);
            LocalTime endTime = LocalTime.parse("00000".substring(splitTime[1].length()) + splitTime[1], this.formatter);
            beginTime.plusHours(timeModifier.getHour());
            beginTime.plusMinutes(timeModifier.getMinute());
            endTime.plusHours(timeModifier.getHour());
            endTime.plusMinutes(timeModifier.getMinute());
            if(time.compareTo(beginTime) >= 0 && time.compareTo(endTime) <= 0){
                opened = true;
            }
        }
        return opened;
    }

    public boolean getMarketOpenCurrently(String market){
        return getMarketOpenAtHour(market, LocalTime.now());
    }

    private LocalTime getTimeModifierFromTimeZone(String timezone){
        LocalTime modifier = LocalTime.parse(timezone.substring(4));
        if(timezone.charAt(3) == '-'){
            modifier = modifier.withHour(maxHourOfDay - modifier.getHour());
            modifier = modifier.withMinute(maxMinuteOfDay - modifier.getMinute());
        }
        return modifier;
    }
}
