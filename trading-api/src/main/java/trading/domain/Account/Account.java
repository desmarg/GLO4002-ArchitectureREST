package trading.domain.Account;

import trading.domain.Credits.Credits;
import trading.domain.FocusArea;
import trading.domain.InvestorProfile;
import trading.domain.ProfileType;

import java.util.ArrayList;

public class Account {
    private AccountNumber accountNumber;
    private Long investorId;
    private InvestorProfile investorProfile;
    private String investorName;
    private Credits credits;

    public Account(
            Long investorId,
            String investorName,
            Credits credits
    ) {
        this.validateInitialCredits(credits);
        this.investorId = investorId;
        this.investorName = investorName;
        this.credits = credits;
        this.investorProfile = new InvestorProfile(
                ProfileType.CONSERVATIVE,
                new ArrayList<FocusArea>()
        );
    }

    public void validateInitialCredits(Credits credits) {
        Credits nullCredits = new Credits();
        if (credits.compareTo(nullCredits) < 0) {
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
        return this.credits.compareTo(transactionPrice) >= 0;
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
