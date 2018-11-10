package trading.domain.Account;

import trading.domain.Credits.Credits;
import trading.domain.InvestorProfile;
import trading.domain.ProfileType;
import trading.domain.transaction.Transaction;

import java.util.ArrayList;

public class Account {
    private final Long investorId;
    private final InvestorProfile investorProfile;
    private final String investorName;
    private final Credits credits;
    private AccountNumber accountNumber;

    public Account(
            Long investorId,
            String investorName,
            Credits credits
    ) {
        this.investorId = investorId;
        this.investorName = investorName;
        this.credits = credits;
        this.investorProfile = new InvestorProfile(
                ProfileType.CONSERVATIVE,
                new ArrayList<String>()
        );
    }

    public void buyTransaction(Transaction transaction) {
        if (this.credits.compareTo(transaction.getTotalPrice()) < 0) {
            throw new NotEnoughCreditsException(transaction.getTransactionNumber());
        }
        this.credits.subtract(transaction.getTotalPrice());
    }

    public void sellTransaction(Transaction transaction) {
        if (this.credits.compareTo(transaction.getFees()) < 0) {
            throw new NotEnoughCreditsForFeesException();
        }
        this.credits.subtract(transaction.getFees());
        this.credits.add(transaction.getPrice());
    }

    public void addCredits(Credits transactionPrice) {
        this.credits.add(transactionPrice);
    }

    public AccountNumber getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(AccountNumber accountNumber) {
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
}
