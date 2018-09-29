package domain.account;

import domain.Credits;
import domain.investorprofile.InvestorProfile;
import domain.investorprofile.ProfileType;
import domain.transaction.Transaction;
import domain.transaction.TransactionId;
import exception.InvalidCreditsAmountException;
import exception.NotEnoughCreditsException;
import exception.TransactionNotFoundException;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Account {
    private AccountNumber accountNumber;
    private Long investorId;
    private InvestorProfile investorProfile;
    private String investorName;
    private String email;
    private Credits credits;
    private Map<TransactionId, Transaction> transactionList;
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
        this.transactionList = new HashMap<>();
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
            throw new NotEnoughCreditsException(transaction.getTransactionId());
        }
        this.credits.subtract(transactionPrice);
        this.transactionList.put(transaction.getTransactionId(), transaction);

        this.stockWallet.put(transaction.getTransactionId(), transaction.getQuantity());
    }

    public Transaction getTransaction(TransactionId transactionId) {
        Transaction transaction = transactionList.get(transactionId);
        if (transaction == null) {
            throw new TransactionNotFoundException(transactionId);
        }
        return transaction;
    }
}
