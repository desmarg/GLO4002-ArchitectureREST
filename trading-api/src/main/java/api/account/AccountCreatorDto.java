package api.account;

import java.math.BigDecimal;

public class AccountCreatorDto extends AccountDto {
    private String investorName;
    private String email;

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInvestorName() {
        return investorName;
    }

    public String getEmail() {
        return email;
    }
}
