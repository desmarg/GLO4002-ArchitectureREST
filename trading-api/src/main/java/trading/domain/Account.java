package trading.domain;

import trading.exception.InvalidAccountInfoException;
import trading.exception.InvalidCreditsAmountException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account {
    private AccountNumber accountNumber;
    private Long investorId;
    private InvestorProfile investorProfile;
    private String investorName;
    private String email;
    private Credits credits;

    public Account(
            Long investorId,
            String investorName,
            String email,
            Credits credits
    ) {
        this.validateEmail(email);
        this.validateInitialCredits(credits);
        this.validateInvestorName(investorName);
        this.investorId = investorId;
        this.investorName = investorName;
        this.email = email;
        this.credits = credits;
        this.investorProfile = new InvestorProfile(
                ProfileType.CONSERVATIVE,
                new ArrayList<FocusArea>()
        );
    }

    public void validateEmail(String email) {
        Pattern emailPattern = Pattern.compile("[A-z0-9._%+-]{2,20}@[A-z0-9]{2,20}\\.[A-z]{2,10}");
        Matcher emailMatcher = emailPattern.matcher(email);
        if (!emailMatcher.matches()) {
            throw new InvalidAccountInfoException("invalid email address");
        }
    }

    public void validateInvestorName(String investorName) {
        Pattern namePattern = Pattern.compile(
                "^[a-zA-Z'\\u00C0-\\u017F]+[ a-zA-Z'\\u00C0-\\u017F]+$"
        );
        Matcher nameMatcher = namePattern.matcher(investorName);
        if (!nameMatcher.matches()) {
            throw new InvalidAccountInfoException("invalid investorName");
        }
    }

    public void validateInitialCredits(Credits credits) {
        Credits nullCredits = new Credits();
        if (credits.compareTo(nullCredits) != 1) {
            throw new InvalidCreditsAmountException();
        }
    }

    public void subtractCredits(Credits transactionPrice) {
        this.credits.subtract(transactionPrice);
    }

    public void addCredits(Credits transactionPrice) {
        this.credits.add(transactionPrice);
    }

    public boolean hasEnoughCreditsToPay(Credits transactionPrice) {
        return this.credits.compareTo(transactionPrice) != -1;
    }

    public AccountNumber getAccountNumber() {
        return this.accountNumber;
    }

    public String getLongAccountNumber() {
        return this.accountNumber.getId();
    }

    public InvestorProfile getInvestorProfile() {
        return this.investorProfile;
    }

    public Long getInvestorId() {
        return this.investorId;
    }

    public Credits getCredits() {
        return this.credits;
    }

    public String getInvestorName() {
        return this.investorName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setAccountNumber(AccountNumber accountNumber) {
        this.accountNumber = accountNumber;
    }

    public boolean hasEnoughCreditsToPaySellFees(Credits sellPrice, Credits feesToPay) {
        Credits balanceAfterTransaction = new Credits();
        balanceAfterTransaction.add(sellPrice);
        balanceAfterTransaction.add(this.credits);

        return balanceAfterTransaction.compareTo(feesToPay) != -1;
    }
}
