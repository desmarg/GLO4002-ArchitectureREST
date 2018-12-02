package trading.api.response;

import trading.domain.Credits;
import trading.domain.InvestorProfile;
import trading.domain.account.Account;

import java.math.BigDecimal;
import java.util.ArrayList;


public class AccountResponseDTO {
    public InvestorProfile investorProfile;
    public String accountNumber;
    public Long investorId;
    public ArrayList<Credits> credits;
    public BigDecimal total;
}
