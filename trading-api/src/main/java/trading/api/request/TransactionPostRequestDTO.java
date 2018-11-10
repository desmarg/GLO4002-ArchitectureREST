package trading.api.request;

import trading.domain.DateTime.DateTime;
import trading.domain.Stock;
import trading.domain.transaction.TransactionNumber;

import javax.validation.constraints.NotNull;
import java.time.Instant;

public class TransactionPostRequestDTO {
    @NotNull
    public String type;
    @NotNull
    public Instant date;
    @NotNull
    public Stock stock;
    @NotNull
    public Long quantity;
    public TransactionNumber transactionNumber;
}
