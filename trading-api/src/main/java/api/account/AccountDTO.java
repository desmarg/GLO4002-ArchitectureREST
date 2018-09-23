package api.account;

import java.math.BigDecimal;

public abstract class AccountDTO {
    private Long investorId;
    private BigDecimal credits;


    public Long getInvestorId() {
        return investorId;
    }

    public void setInvestorId(Long investorId) {
        this.investorId = investorId;
    }

    public BigDecimal getCredits() {
        return credits;
    }

    public void setCredits(BigDecimal credits) {
        this.credits = credits;
    }
}
