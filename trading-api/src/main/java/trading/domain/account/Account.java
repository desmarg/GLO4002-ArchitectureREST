package trading.domain.account;

import trading.domain.*;
import trading.domain.transaction.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Account {

    private final AccountNumber accountNumber;
    private final Long investorId;
    private final InvestorProfile investorProfile;
    private final String investorName;
    private final Map<TransactionNumber, Long> remainingStocksMap;
    private final HashMap<Currency, Credits> initialCredits;
    private HashMap<Currency, Credits> creditMap;

    public Account(Long investorId, String investorName, HashMap<Currency, Credits> creditMap, Integer id) {
        this(
                investorId,
                investorName,
                creditMap,
                creditMap,
                new InvestorProfile(ProfileType.CONSERVATIVE, new ArrayList<>()),
                new HashMap<>(),
                new AccountNumber(investorName, id)
        );
    }

    public Account(Long investorId, String investorName, HashMap<Currency, Credits> creditMap, HashMap<Currency, Credits> initialCredits,
                   InvestorProfile investorProfile,
                   Map<TransactionNumber, Long> remainingStocksMap, AccountNumber accountNumber) {
        this.investorId = investorId;
        this.investorName = investorName;
        this.creditMap = creditMap;
        this.initialCredits = initialCredits;
        this.investorProfile = investorProfile;
        this.remainingStocksMap = remainingStocksMap;
        this.accountNumber = accountNumber;
    }

    public void buyTransaction(TransactionBuy transactionBuy) {
        this.payTransactionFees(transactionBuy.getValueWithFees());
        this.remainingStocksMap.put(transactionBuy.getTransactionNumber(),
                transactionBuy.getQuantity());
    }

    public void sellTransaction(TransactionSell transactionSell, TransactionBuy referredTransaction) {
        this.validateStockParameter(transactionSell, referredTransaction);
        this.sellStocksIfAccountHasEnough(referredTransaction, transactionSell.getQuantity());
        this.payTransactionFees(transactionSell.getFees());
        this.gainTransactionValue(transactionSell);
    }

    private void gainTransactionValue(TransactionSell transactionSell) {
        Credits transactionValue = transactionSell.getValue();
        this.addCredits(transactionValue);
    }

    private void payTransactionFees(Credits fees) {
        Credits transactionFees = fees;
        this.subtractCreditsIfHasEnough(transactionFees);
    }

    private void validateStockParameter(TransactionSell transactionSell, TransactionBuy referredTransaction) {
        if (!transactionSell.getStock().equals(referredTransaction.getStock())) {
            throw new StockParametersDontMatchException();
        }
    }

    private void sellStocksIfAccountHasEnough(TransactionBuy transactionBuy, Long quantity) {
        Long remainingStocks = this.remainingStocksMap.get(transactionBuy.getTransactionNumber());
        if (remainingStocks == null) {
            throw new InvalidTransactionNumberException();
        } else if (remainingStocks < quantity) {
            throw new NotEnoughStockException(transactionBuy.getStock());
        }
        this.remainingStocksMap.put(transactionBuy.getTransactionNumber(),
                remainingStocks - quantity);
    }

    public AccountNumber getAccountNumber() {
        return this.accountNumber;
    }

    public InvestorProfile getInvestorProfile() {
        return this.investorProfile;
    }

    public Long getInvestorId() {
        return this.investorId;
    }

    public HashMap<Currency, Credits> getCredits() {
        return this.creditMap;
    }

    public String getInvestorName() {
        return this.investorName;
    }

    public Map<TransactionNumber, Long> getRemainingStocksMap() {
        return this.remainingStocksMap;
    }

    public HashMap<Currency, Credits> getInitialCredits() {
        return this.initialCredits;
    }

    public BigDecimal getTotalCreditsInCAD(ForeignExchangeRepository forexRepo) {
        return forexRepo.calculateCreditSumInCAD(this.creditMap);
    }

    private boolean hasEnoughCreditsOfCurrency(Credits creditsToCompare) {
        Currency creditCurrency = creditsToCompare.getCurrency();
        if (!this.creditMap.containsKey(creditCurrency)) {
            return false;
        }
        Credits ownedCredits = this.creditMap.get(creditCurrency);
        if (ownedCredits.isSmaller(creditsToCompare)) {
            return false;
        }
        return true;
    }

    private void subtractCreditsIfHasEnough(Credits creditsToSubtract) {
        if (!this.hasEnoughCreditsOfCurrency(creditsToSubtract)) {
            throw new NotEnoughCreditsException();
        }
        Currency creditCurrency = creditsToSubtract.getCurrency();
        this.creditMap.merge(creditCurrency, creditsToSubtract, Credits::subtract);
    }

    private void addCredits(Credits creditsToAdd) {
        Currency creditCurrency = creditsToAdd.getCurrency();
        this.creditMap.merge(creditCurrency, creditsToAdd, Credits::add);
    }
}
