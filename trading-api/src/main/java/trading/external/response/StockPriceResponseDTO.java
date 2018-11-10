package trading.external.response;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;

public class StockPriceResponseDTO {
    @NotNull
    public Instant date;
    @NotNull
    public BigDecimal price;
}
