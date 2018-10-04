package api.account;

import domain.account.Account;
import domain.investorprofile.InvestorProfile;

public class AccountGetDto extends AccountDto {

    private InvestorProfile investorProfile;
    private String accountNumber;

    public AccountGetDto() {
    }

    public AccountGetDto(Account account) {
        super(account);
        this.investorProfile = account.getInvestorProfile();
        this.accountNumber = account.getAccountNumber().getId();
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
}
