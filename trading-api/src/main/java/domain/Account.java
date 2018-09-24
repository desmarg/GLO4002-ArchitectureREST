package domain;

import api.account.InvalidCreditsAmountException;
import domain.investorprofile.InvestorProfile;
import java.math.BigDecimal;

public class Account {
    private Long accountNumber;
    private Long investorId;
    private InvestorProfile investorProfile;
    private String investorName;
    private String email;
    private BigDecimal credits;
    private static long accountNumberCounter = 0L;

    public Account(Long investorId, String investorName, String email, BigDecimal credits) {
        this.investorId = investorId;
        this.investorName = investorName;
        this.email = email;
        this.credits = validateCreditsAmount(credits);
        this.investorProfile = new InvestorProfile();
        this.accountNumber = accountNumberCounter;
        this.accountNumberCounter++;
    }

    private BigDecimal validateCreditsAmount(BigDecimal credits) {
        if (credits.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidCreditsAmountException("Number of credits has to be greater than 0.");
        }
        return credits;
    }

    public long getAccountNumber() {
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

    public void setInvestorId(Long investorId) {
        this.investorId = investorId;
    }

    public String getInvestorName() {
        return investorName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getCredits() {
        return credits;
    }

    public void setCredits(BigDecimal credits) {
        this.credits = validateCreditsAmount(credits);
    }
}
