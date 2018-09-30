package domain.account;

import domain.Credits;
import domain.investorprofile.InvestorProfile;
import domain.investorprofile.ProfileType;
import domain.transaction.Transaction;
import domain.transaction.TransactionNumber;
import exception.NotEnoughCreditsException;
import exception.TransactionNotFoundException;

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
    private Map<TransactionNumber, Transaction> transactionList;
    private Map<TransactionNumber, Long> stockWallet;

    public Account(
            Long investorId,
            String investorName,
            String email,
            Credits credits,
            AccountNumber accountNumber
    ) {
        this.investorId = investorId;
        this.investorName = investorName;
        this.email = email;
        this.credits = credits;
        this.investorProfile = new InvestorProfile(ProfileType.CONSERVATIVE, new ArrayList<>());
        this.accountNumber = accountNumber;
        this.transactionList = new HashMap<>();
        this.stockWallet = new HashMap<>();
    }

    public void buyTransaction(Transaction transaction) {
        Credits transactionPrice = transaction.calculateTransactionPrice();
        if (this.credits.compareTo(transactionPrice) < 0) {
            throw new NotEnoughCreditsException(transaction.getTransactionNumber());
        }
        this.credits.subtract(transactionPrice);
        this.transactionList.put(transaction.getTransactionNumber(), transaction);
        this.stockWallet.put(transaction.getTransactionNumber(), transaction.getQuantity());
    }

    public void sellTransaction(Transaction transaction) {
    }

    public Transaction getTransaction(TransactionNumber transactionNumber) {
        Transaction transaction = this.transactionList.get(transactionNumber);
        if (transaction == null) {
            throw new TransactionNotFoundException(transactionNumber);
        }
        return transaction;
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

    public Map<TransactionNumber, Transaction> getTransactionList() {
        return this.transactionList;
    }

    public Map<TransactionNumber, Long> getStockWallet() {
        return this.stockWallet;
    }
}
