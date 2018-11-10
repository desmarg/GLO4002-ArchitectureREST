package trading.domain.Account;

import trading.domain.Credits.Credits;
import trading.domain.InvestorProfile;
import trading.domain.ProfileType;

import java.util.ArrayList;

public class Account {
    private final Long investorId;
    private final InvestorProfile investorProfile;
    private final String investorName;
    private final Credits credits;
    private AccountNumber accountNumber;

    public Account(
            final Long investorId,
            final String investorName,
            final Credits credits
    ) {
        this.validateInitialCredits(credits);
        this.investorId = investorId;
        this.investorName = investorName;
        this.credits = credits;
        this.investorProfile = new InvestorProfile(
                ProfileType.CONSERVATIVE,
                new ArrayList<String>()
        );
    }

    public void validateInitialCredits(final Credits credits) {
        final Credits nullCredits = new Credits();
        if (credits.compareTo(nullCredits) < 0) {
            throw new InvalidCreditsAmountException();
        }
    }

    public void subtractCredits(final Credits transactionPrice) {
        this.credits.subtract(transactionPrice);
    }

    public void addCredits(final Credits transactionPrice) {
        this.credits.add(transactionPrice);
    }

    public boolean hasEnoughCreditsToPay(final Credits transactionPrice) {
        return this.credits.compareTo(transactionPrice) >= 0;
    }

    public AccountNumber getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(final AccountNumber accountNumber) {
        this.accountNumber = accountNumber;
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

    public boolean hasEnoughCreditsToPaySellFees(final Credits sellPrice, final Credits feesToPay) {
        final Credits balanceAfterTransaction = new Credits();
        balanceAfterTransaction.add(sellPrice);
        balanceAfterTransaction.add(this.credits);

        return balanceAfterTransaction.compareTo(feesToPay) != -1;
    }
}
