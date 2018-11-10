package trading.api.request;

import trading.domain.DateTime.DateTime;
import trading.domain.Stock;
import trading.domain.transaction.TransactionNumber;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

public class TransactionPostRequestDTO {
    @NotNull
    public String type;
    @NotNull
    public Instant date;
    @NotNull
    public StockDTO stock;
    @NotNull
    public Long quantity;
    public UUID transactionNumber;
}
