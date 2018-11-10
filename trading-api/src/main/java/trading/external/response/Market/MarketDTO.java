package trading.external.response.Market;

import javax.validation.constraints.NotNull;
import java.util.List;

public class MarketDTO {
    @NotNull
    public List<String> openHours;
    @NotNull
    public String symbol;
    @NotNull
    public String timezone;
}
