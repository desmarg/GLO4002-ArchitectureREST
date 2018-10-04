package api.response;


import domain.Account;
import domain.InvestorProfile;


public class AccountResponse{

    private InvestorProfile investorProfile;
    private String accountNumber;
    private Long investorId;
    private float credits;
    
    public AccountResponse() {
    }

    public AccountResponse(Account account) {
        this.investorProfile = account.getInvestorProfile();
        this.accountNumber = account.getAccountNumber().getId();
        this.investorId = account.getInvestorId();
        this.credits = account.getCredits().valueToFloat();
    }

    public void setInvestorProfile(InvestorProfile investorProfile) {
        this.investorProfile = investorProfile;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public InvestorProfile getInvestorProfile() {
        return this.investorProfile;
    }

    public String getAccountNumber() {
        return this.accountNumber;
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
