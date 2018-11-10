package trading.api.response;

import trading.domain.Account.Account;
import trading.domain.InvestorProfile;


public class AccountResponse {
    private InvestorProfile investorProfile;
    private String accountNumber;
    private Long investorId;
    private Float credits;

    public AccountResponse(Account account) {
        this.investorProfile = account.getInvestorProfile();
        this.accountNumber = account.getResponseId();
        this.investorId = account.getInvestorId();
        this.credits = account.getCredits().valueToFloat();
    }

    public InvestorProfile getInvestorProfile() {
        return this.investorProfile;
    }

    public void setInvestorProfile(InvestorProfile investorProfile) {
        this.investorProfile = investorProfile;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

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
