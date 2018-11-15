package trading.domain.Account;

import trading.domain.Credits.Credits;
import trading.domain.InvestorProfile;
import trading.domain.ProfileType;
import trading.domain.transaction.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Account {

    private final int id;
    private final Long investorId;
    private final InvestorProfile investorProfile;
    private final String investorName;
    private final Credits credits;
    private final Map<TransactionNumber, Long> remainingStocksMap;

    public Account(
            Long investorId,
            String investorName,
            Credits credits,
            int id
    ) {
        this.investorId = investorId;
        this.investorName = investorName;
        this.credits = credits;
        this.investorProfile = new InvestorProfile(
                ProfileType.CONSERVATIVE,
                new ArrayList<>()
        );
        this.remainingStocksMap = new HashMap<>();
        this.id = id;
    }

    public Account(
            Long investorId,
            String investorName,
            Credits credits,
            InvestorProfile investorProfile,
            Map<TransactionNumber, Long> remainingStocksMap,
            int id
    ) {
        this.investorId = investorId;
        this.investorName = investorName;
        this.credits = credits;
        this.investorProfile = investorProfile;
        this.remainingStocksMap = remainingStocksMap;
        this.id = id;
    }

    public void buyTransaction(TransactionBuy transactionBuy) {
        Credits totalPrice = transactionBuy.getValueWithFees();
        if (this.credits.compareTo(totalPrice) < 0) {
            throw new NotEnoughCreditsException(transactionBuy.getTransactionNumber());
        }
        this.credits.subtract(totalPrice);
        this.remainingStocksMap.put(transactionBuy.getTransactionNumber(), transactionBuy.getQuantity());
    }

    public void sellTransaction(TransactionSell transactionSell, TransactionBuy
            referredTransaction) {
        if (!transactionSell.getStock().equals(referredTransaction.getStock())) {
            throw new StockParametersDontMatchException();
        }

        this.deduceStocks(referredTransaction, transactionSell.getQuantity());
        if (this.credits.compareTo(transactionSell.getFees()) < 0) {
            throw new NotEnoughCreditsForFeesException();
        }
        this.credits.subtract(transactionSell.getFees());
        this.credits.add(transactionSell.getValue());
    }

    private void deduceStocks(TransactionBuy transactionBuy, Long quantity) {
        Long remainingStocks = this.remainingStocksMap.get(transactionBuy.getTransactionNumber());
        if (remainingStocks == null) {
            throw new InvalidTransactionNumberException(transactionBuy.getTransactionNumber());
        } else if (remainingStocks < quantity) {
            throw new NotEnoughStockException(transactionBuy.getStock());
        }
        this.remainingStocksMap.put(transactionBuy.getTransactionNumber(), remainingStocks - quantity);
    }

    public AccountNumber getAccountNumber() {
        return new AccountNumber(this.investorName, this.id);
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

    public Map<TransactionNumber, Long> getRemainingStocksMap() {
        return this.remainingStocksMap;
    }

    public int getId() {
        return this.id;
    }
}
