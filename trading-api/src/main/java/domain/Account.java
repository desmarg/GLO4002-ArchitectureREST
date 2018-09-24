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

    public InvestorProfile getInvestorProfile() {
        return investorProfile;
    }

    public Long getInvestorId() {
        return investorId;
    }

    public BigDecimal getCredits() {
        return credits;
    }
}
