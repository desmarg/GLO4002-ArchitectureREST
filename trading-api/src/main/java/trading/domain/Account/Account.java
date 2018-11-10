package trading.domain.Account;

import trading.domain.Credits.Credits;
import trading.domain.InvestorProfile;
import trading.domain.ProfileType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table(name = "ACCOUNTS")
public class Account implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Long investorId;
    @OneToOne(targetEntity = InvestorProfile.class)
    private InvestorProfile investorProfile;
    @Column
    private String investorName;
    @OneToOne(targetEntity = Credits.class)
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
                new ArrayList<String>()
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

    public Long getId() {
        return this.id;
    }

    public String getResponseId() {
        return this.makeId();
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

    public void setId(Long id) {
        this.id = id;
    }

    public boolean hasEnoughCreditsToPaySellFees(Credits sellPrice, Credits feesToPay) {
        Credits balanceAfterTransaction = new Credits();
        balanceAfterTransaction.add(sellPrice);
        balanceAfterTransaction.add(this.credits);

        return balanceAfterTransaction.compareTo(feesToPay) != -1;
    }

    private String makeInitials(String name) {
        return name.replaceAll("([^\\s])[^\\s]+", "$1").replaceAll("\\s", "").toUpperCase();
    }

    private String makeId() {
        return this.makeInitials(this.investorName) + "-" + String.format("%04d", this.id);
    }
}
