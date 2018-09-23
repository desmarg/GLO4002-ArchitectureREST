package domain;

import api.account.InvalidCreditsAmountException;
import domain.investorprofile.InvestorProfile;
import java.math.BigDecimal;

public class Account {
    private Long investorId;
    private String investorName;
    private String email;
    private BigDecimal credits;
    private InvestorProfile investorProfile;
    private static Long accountNumber = 0L;

    public Account(Long investorId, String investorName, String email, BigDecimal credits) {
        this.investorId = investorId;
        this.investorName = investorName;
        this.email = email;
        this.credits = validateCreditsAmount(credits);
        this.investorProfile = new InvestorProfile();
        this.accountNumber++;
    }

    private BigDecimal validateCreditsAmount(BigDecimal credits){
        if (credits.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidCreditsAmountException("Number of credits has to be greater than 0.");
        }
        return credits;
    }
    public Long getAccountNumber(){
        return this.accountNumber;
    }

    public void setInvestorName(String newName) {
        this.investorName = newName;
    }

    public InvestorProfile getInvestorProfile() {
        return investorProfile;
    }

    public Long getInvestorId() {
        return investorId;
    }

    public String getInvestorName() {
        return investorName;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getCredits() {
        return credits;
    }
}
