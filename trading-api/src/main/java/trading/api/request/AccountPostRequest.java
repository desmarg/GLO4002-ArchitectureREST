package trading.api.request;

import trading.domain.Account;
import trading.exception.MissingFieldException;

public class AccountPostRequest {
    private String investorName;
    private String email;
    private Long investorId;
    private Float credits;

    public AccountPostRequest() {
    }

    public AccountPostRequest(Account account) {
        this.investorName = account.getInvestorName();
        this.email = account.getEmail();
        this.investorId = account.getInvestorId();
        this.credits = account.getCredits().valueToFloat();
    }

    public void nullCheck() {
        if (investorName == null) {
            throw new MissingFieldException("investorName");
        }
        if (email == null) {
            throw new MissingFieldException("email");
        }
        if (investorId == null) {
            throw new MissingFieldException("investorId");
        }
        if (credits == null) {
            throw new MissingFieldException("credits");
        }
    }

    public String getInvestorName() {
        return this.investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getInvestorId() {
        return this.investorId;
    }

    public void setInvestorId(Long investorId) {
        this.investorId = investorId;
    }

    public Float getCredits() {
        return this.credits;
    }

    public void setCredits(Float credits) {
        this.credits = credits;
    }
}
