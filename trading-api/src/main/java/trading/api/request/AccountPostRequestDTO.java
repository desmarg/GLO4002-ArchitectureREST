package trading.api.request;

import trading.domain.Credits.Credits;

import javax.validation.constraints.NotNull;

public class AccountPostRequestDTO {
    @NotNull
    public String investorName;
    @NotNull
    public Long investorId;
    @NotNull
    public Credits credits;
    @NotNull
    public String email;
}
