package trading.api.request;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;

public class AccountPostRequestDTO {
    @NotNull
    public String investorName;
    @NotNull
    public Long investorId;
    @NotNull
    public ArrayList<CreditsDTO> credits;
    @NotNull
    public String email;
}
