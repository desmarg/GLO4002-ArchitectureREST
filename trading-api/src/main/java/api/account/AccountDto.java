package api.account;

import domain.account.Account;

public abstract class AccountDto {
    private Long investorId;
    private float credits;

    public AccountDto() {
    }

    public AccountDto(Account account) {
        this.investorId = account.getInvestorId();
        this.credits = account.getCredits().valueToFloat();
    }

    public Long getInvestorId() {
        return this.investorId;
    }

    public void setInvestorId(Long investorId) {
        this.investorId = investorId;
    }

    public float getCredits() {
        return this.credits;
    }

    public void setCredits(Float credits) {
        this.credits = credits;
    }
}
