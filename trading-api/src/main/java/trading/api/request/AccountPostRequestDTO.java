package trading.api.request;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class AccountPostRequestDTO {
    @NotNull
    public String investorName;
    @NotNull
    public Long investorId;
    @NotNull
    public BigDecimal credits;
    @NotNull
    public String email;
}
