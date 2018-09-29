package api.account;

import domain.investorprofile.InvestorProfile;

public class AccountGetDto extends AccountDto {
    private InvestorProfile investorProfile;
    private long accountNumber;

    public InvestorProfile getInvestorProfile() {
        return this.investorProfile;
    }

    public void setInvestorProfile(InvestorProfile investorProfile) {
        this.investorProfile = investorProfile;
    }

    public long getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }
}
