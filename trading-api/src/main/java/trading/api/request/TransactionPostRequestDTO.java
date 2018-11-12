package trading.api.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import trading.api.configuration.CustomInstantDeserializer;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

public class TransactionPostRequestDTO {
    @NotNull
    public String type;
    @NotNull
    @JsonDeserialize(using = CustomInstantDeserializer.class)
    public Instant date;
    @NotNull
    public StockDTO stock;
    @NotNull
    public Long quantity;
    public UUID transactionNumber;
}
