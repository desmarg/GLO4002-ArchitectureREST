package trading.external.response;

import javax.validation.constraints.NotNull;
import java.util.List;

public class StockApiDTO {
    @NotNull
    public Long id;
    @NotNull
    public String market;
    @NotNull
    public String symbol;
    @NotNull
    public String type;
    @NotNull
    public List<StockPriceResponseDTO> prices;
}
