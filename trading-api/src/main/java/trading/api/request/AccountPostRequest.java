package trading.api.request;

import trading.domain.Account;

public class AccountPostRequest {
    private String investorName;
    private String email;
    private Long investorId;
    private float credits;

    public AccountPostRequest() {}

    public AccountPostRequest(Account account) {
        this.investorName = account.getInvestorName();
        this.email = account.getEmail();
        this.investorId = account.getInvestorId();
        this.credits = account.getCredits().valueToFloat();
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInvestorName() {
        return this.investorName;
    }

    public String getEmail() {
        return this.email;
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
