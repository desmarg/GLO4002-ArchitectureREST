package api.account;

import domain.investorprofile.InvestorProfile;

public class GetAccountDto extends AccountDto {

    private InvestorProfile investorProfile;
    private long accountNumber;

    public void setInvestorProfile(InvestorProfile investorProfile) {
        this.investorProfile = investorProfile;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public InvestorProfile getInvestorProfile() {
        return investorProfile;
    }

    public long getAccountNumber() {
        return accountNumber;
    }
}
