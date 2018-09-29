package domain;

import api.account.InvalidCreditsAmountException;
import domain.investorprofile.InvestorProfile;

import java.math.BigDecimal;
import java.security.InvalidParameterException;

public class Account {
    private AccountNumber accountNumber;
    private Long investorId;
    private InvestorProfile investorProfile;
    private String investorName;
    private String email;
    private BigDecimal credits;

    public Account(
            Long investorId,
            String investorName,
            String email,
            BigDecimal credits,
            Long accountNumber,
            InvestorProfile investorProfile
    ) {
        if (investorId == null) {
            throw new InvalidParameterException("investorId cannot be null");
        } else if (investorId < 0) {
            throw new InvalidParameterException("investorId cannot be negative");
        }

        if (investorName == null) {
            throw new InvalidParameterException("investorName cannot be null");
        } else if (investorName.isEmpty()) {
            throw new InvalidParameterException("investorName cannot be empty");
        }

        if (email == null) {
            throw new InvalidParameterException("email cannot be null");
        } else if (email.isEmpty()) {
            throw new InvalidParameterException("email cannot be empty");
        }

        if (credits == null) {
            throw new InvalidParameterException("credits cannot be null");
        } else if (credits.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidCreditsAmountException();
        }

        if (accountNumber == null) {
            throw new InvalidParameterException("accountNumber cannot be null");
        } else if (accountNumber < 0) {
            throw new InvalidParameterException("accountNumber cannot be negative");
        }

        if (investorProfile == null) {
            throw new InvalidParameterException("investorProfile cannot be null");
        }

        this.investorId = investorId;
        this.investorName = investorName;
        this.email = email;
        this.credits = credits;
        this.investorProfile = investorProfile;
        this.accountNumber = new AccountNumber(accountNumber);
    }

    public AccountNumber getAccountNumber() {
        return this.accountNumber;
    }

    public InvestorProfile getInvestorProfile() {
        return this.investorProfile;
    }

    public Long getInvestorId() {
        return this.investorId;
    }

    public BigDecimal getCredits() {
        return this.credits;
    }
}
