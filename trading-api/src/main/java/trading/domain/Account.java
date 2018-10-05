package trading.domain;

import trading.api.request.AccountPostRequest;
import trading.domain.transaction.Transaction;
import trading.domain.transaction.TransactionNumber;
import trading.exception.InvalidAccountInfoException;
import trading.exception.InvalidCreditsAmountException;
import trading.exception.TransactionNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account {
    private AccountNumber accountNumber;
    private Long investorId;
    private InvestorProfile investorProfile;
    private String investorName;
    private String email;
    private Credits credits;
    private Map<TransactionNumber, Transaction> transactionList;

    public Account(
            Long investorId,
            String investorName,
            String email,
            Credits credits,
            AccountNumber accountNumber
    ) {
        this.dataValidator(investorId, investorName, email, credits);
        this.investorId = investorId;
        this.investorName = investorName;
        this.email = email;
        this.credits = credits;
        this.accountNumber = accountNumber;
        this.investorProfile = new InvestorProfile(ProfileType.CONSERVATIVE, new ArrayList<FocusArea>());
        this.transactionList = new HashMap<>();
    }

    public static Account fromRequest(AccountPostRequest accountPostRequest, long accountNumber) {
        return new Account(
                accountPostRequest.getInvestorId(),
                accountPostRequest.getInvestorName(),
                accountPostRequest.getEmail(),
                Credits.fromDouble(accountPostRequest.getCredits()),
                new AccountNumber(accountPostRequest.getInvestorName(), accountNumber)
        );
    }

    private void dataValidator(
            Long investorId,
            String investorName,
            String email,
            Credits credits
    ) {
        if (investorId == null) {
            throw new InvalidAccountInfoException("investorId cannot be null");
        }
        if (investorId < 0 || investorId == 0) {
            throw new InvalidAccountInfoException("investorId cannot be lesser or equal to 0");
        }
        Pattern namePattern = Pattern.compile("^[a-zA-Z'\\u00C0-\\u017F]+[ a-zA-Z'\\u00C0-\\u017F]+$");
        Matcher nameMatcher = namePattern.matcher(investorName);
        if (!nameMatcher.matches()) {
            throw new InvalidAccountInfoException("invalid investorName");
        }

        Pattern emailPattern = Pattern.compile("[A-z0-9._%+-]{2,20}@[A-z0-9]{2,20}\\.[A-z]{2,10}");
        Matcher emailMatcher = emailPattern.matcher(email);
        if (!emailMatcher.matches()) {
            throw new InvalidAccountInfoException("invalid email address");
        }
        if (credits == null) {
            throw new InvalidAccountInfoException("credits cannot be null");
        }
        if (
                credits.amount.compareTo(BigDecimal.ZERO) == 0 ||
                        credits.amount.compareTo(BigDecimal.ZERO) < 0
        ) {
            throw new InvalidCreditsAmountException();
        }
    }


    public void subtractCredits(Credits transactionPrice) {
        this.credits.subtract(transactionPrice);
    }

    public void addCredits(Credits transactionPrice) {
        this.credits.add(transactionPrice);
    }

    public void addTransaction(Transaction transaction) {
        this.transactionList.put(transaction.getTransactionNumber(), transaction);
    }

    public Map<TransactionNumber, Transaction> getTransactionList() {
        return this.transactionList;
    }

    public boolean hasEnoughCreditsToPay(Credits transactionPrice) {
        return this.credits.compareTo(transactionPrice) >= 0;
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
}