package api.account;

import domain.account.Account;

public class AccountPostDto extends AccountDto {
    private String investorName;
    private String email;

    public AccountPostDto() {
    }

    public AccountPostDto(Account account) {
        super(account);
        this.investorName = account.getInvestorName();
        this.email = account.getEmail();
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
}
