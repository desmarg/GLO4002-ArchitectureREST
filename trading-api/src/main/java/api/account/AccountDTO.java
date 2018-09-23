package api.account;

import java.math.BigDecimal;

public class AccountDTO {

    private Long investorId;
    private String investorName;
    private String email;
    private BigDecimal credits;

    public void setInvestorId(Long investorId) {
        this.investorId = investorId;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCredits(BigDecimal credits) {
        this.credits = credits;
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
