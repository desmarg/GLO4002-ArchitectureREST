package application.market;

import java.util.ArrayList;

public class MarketDto {
    private ArrayList<String> openHours;
    private String symbol;
    private String timezone;

    public ArrayList<String> getOpenHours() {
        return openHours;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getTimezone() {
        return timezone;
    }
}
