package trading.api.request;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreditsDTO {
    @NotNull
    public String currency;
    @NotNull
    public BigDecimal amount;
}