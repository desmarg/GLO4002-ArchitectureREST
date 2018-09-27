package api.account;

import java.math.BigDecimal;

public abstract class AccountDto {
    private long investorId;
    private BigDecimal credits;

    public long getInvestorId() {
        return this.investorId;
    }

    public void setInvestorId(long investorId) {
        this.investorId = investorId;
    }

    public BigDecimal getCredits() {
        return this.credits;
    }

    public void setCredits(BigDecimal credits) {
        this.credits = credits;
    }
}
