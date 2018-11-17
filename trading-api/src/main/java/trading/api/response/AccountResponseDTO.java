package trading.api.response;

import trading.domain.Account.Account;
import trading.domain.InvestorProfile;

import java.math.BigDecimal;


public class AccountResponseDTO {
    public InvestorProfile investorProfile;
    public String accountNumber;
    public Long investorId;
    public BigDecimal credits;

    public AccountResponseDTO(Account account) {
        this.investorProfile = account.getInvestorProfile();
        this.accountNumber = account.getAccountNumber().getString();
        this.investorId = account.getInvestorId();
        this.credits = account.getCredits().toBigDecimal();
    }
}
