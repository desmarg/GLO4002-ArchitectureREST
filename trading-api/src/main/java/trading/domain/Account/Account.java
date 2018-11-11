package trading.domain.Account;

import trading.domain.Credits.Credits;
import trading.domain.InvestorProfile;
import trading.domain.ProfileType;
import trading.domain.Transaction.TransactionBuy;
import trading.domain.Transaction.TransactionSell;

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

    public void buyTransaction(TransactionBuy transactionBuy) {
        if (this.credits.compareTo(transactionBuy.getPriceWithFees()) < 0) {
            throw new NotEnoughCreditsException(transactionBuy.getTransactionNumber());
        }
        this.credits.subtract(transactionBuy.getPriceWithFees());
    }

    public void sellTransaction(TransactionSell transactionSell) {
        if (this.credits.compareTo(transactionSell.getFees()) < 0) {
            throw new NotEnoughCreditsForFeesException();
        }
        this.credits.subtract(transactionSell.getFees());
        this.credits.add(transactionSell.getPrice());
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
