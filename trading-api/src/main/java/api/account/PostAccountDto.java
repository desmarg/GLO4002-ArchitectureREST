package api.account;

public class AccountPostDto extends AccountDto {
    private String investorName;
    private String email;


    public String getInvestorName() {
        return this.investorName;
    }

    public String getEmail() {
        return this.email;
    }
}
