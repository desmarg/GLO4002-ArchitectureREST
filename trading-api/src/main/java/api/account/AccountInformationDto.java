package api.account;

import domain.investorprofile.InvestorProfile;

public class AccountInformationDto extends AccountDto {
    private InvestorProfile investorProfile;
    private Long accountNumber;

    public InvestorProfile getInvestorProfile() {
        return this.investorProfile;
    }

    public void setInvestorProfile(InvestorProfile investorProfile) {
        this.investorProfile = investorProfile;
    }

    public Long getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }
}
