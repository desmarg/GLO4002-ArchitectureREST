package trading.api.request;

import javax.validation.constraints.NotNull;

public class StockDTO {
    @NotNull
    public String market;
    @NotNull
    public String symbol;
}
