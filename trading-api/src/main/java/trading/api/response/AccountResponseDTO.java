package trading.api.response;

import trading.domain.InvestorProfile;
import trading.domain.account.Account;

import java.math.BigDecimal;


public class AccountResponseDTO {
    public final InvestorProfile investorProfile;
    public final String accountNumber;
    public final Long investorId;
    public final BigDecimal credits;

    public AccountResponseDTO(Account account) {
        this.investorProfile = account.getInvestorProfile();
        this.accountNumber = account.getAccountNumber().getString();
        this.investorId = account.getInvestorId();
        this.credits = account.getCredits().toBigDecimal();
    }
}
