package trading.external.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import trading.api.configuration.CustomInstantDeserializer;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;

public class StockPriceResponseDTO {
    @NotNull
    @JsonDeserialize(using = CustomInstantDeserializer.class)
    public Instant date;
    @NotNull
    public BigDecimal price;
}
