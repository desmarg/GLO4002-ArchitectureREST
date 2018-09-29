package api.account;

public abstract class AccountDto {
    private Long investorId;
    private Float credits;

    public Long getInvestorId() {
        return this.investorId;
    }

    public void setInvestorId(Long investorId) {
        this.investorId = investorId;
    }

    public Float getCredits() {
        return this.credits;
    }

    public void setCredits(Float credits) {
        this.credits = credits;
    }
}
