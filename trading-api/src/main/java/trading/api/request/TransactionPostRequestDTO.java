package trading.api.request;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

public class TransactionPostRequestDTO {
    @NotNull
    public String type;
    @NotNull
    public OffsetDateTime date;
    @NotNull
    public StockDTO stock;
    @NotNull
    public Long quantity;
    public UUID transactionNumber;
}
