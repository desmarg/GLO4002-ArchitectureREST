package api.request;

import api.account.AccountDto;
import domain.account.Account;

public class AccountPostRequest extends AccountDto {
    private String investorName;
    private String email;

    public AccountPostRequest() {
    }

    public AccountPostRequest(Account account) {
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
