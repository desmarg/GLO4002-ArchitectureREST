package domain;

import api.account.InvalidCreditsAmountException;
import domain.investorprofile.InvestorProfile;
import domain.investorprofile.ProfileType;
import exception.NotEnoughCreditsException;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Account {
    private AccountNumber accountNumber;
    private Long investorId;
    private InvestorProfile investorProfile;
    private String investorName;
    private String email;
    private Credits credits;
    private List<Transaction> transactionList;
    private Map<TransactionId, Long> stockWallet;

    public Account(
            Long investorId,
            String investorName,
            String email,
            Credits credits,
            Long accountNumber
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
        } else if (credits.compareTo(Credits.fromFloat(0.00)) <= 0) {
            throw new InvalidCreditsAmountException();
        }

        if (accountNumber == null) {
            throw new InvalidParameterException("accountNumber cannot be null");
        } else if (accountNumber < 0) {
            throw new InvalidParameterException("accountNumber cannot be negative");
        }

        this.investorId = investorId;
        this.investorName = investorName;
        this.email = email;
        this.credits = credits;
        this.investorProfile = new InvestorProfile(ProfileType.CONSERVATIVE, new ArrayList<>());
        this.accountNumber = new AccountNumber(accountNumber);
        this.transactionList = new ArrayList<>();
        this.stockWallet = new HashMap<>();
    }

    public AccountNumber getAccountNumber() {
        return this.accountNumber;
    }

    public Long getLongAccountNumber() {
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

    public void makeTransaction(Transaction transaction) {
        Credits transactionPrice = transaction.calculateTransactionPrice();
        if (this.credits.compareTo(transactionPrice) < 0) {
            throw new NotEnoughCreditsException();
        }
        this.credits.subtract(transactionPrice);
        this.transactionList.add(transaction);

        this.stockWallet.put(transaction.getTransactionId(), transaction.getQuantity());
    }
}
