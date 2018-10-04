package api.account;

public class AccountPostDto extends AccountDto {
    private String investorName;
    private String email;

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
