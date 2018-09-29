package api.account;

import java.math.BigDecimal;

public abstract class AccountDto {
    private Long investorId;
    private BigDecimal credits;

    public Long getInvestorId() {
        return this.investorId;
    }

    public void setInvestorId(Long investorId) {
        this.investorId = investorId;
    }

    public BigDecimal getCredits() {
        return this.credits;
    }

    public void setCredits(BigDecimal credits) {
        this.credits = credits;
    }
}
