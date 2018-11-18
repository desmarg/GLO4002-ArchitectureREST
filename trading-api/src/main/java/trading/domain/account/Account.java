package trading.domain.account;

import trading.domain.Credits;
import trading.domain.InvestorProfile;
import trading.domain.ProfileType;
import trading.domain.transaction.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Account {

    private final Integer id;
    private final Long investorId;
    private final InvestorProfile investorProfile;
    private final String investorName;
    private final Map<TransactionNumber, Long> remainingStocksMap;
    private final Credits initialCredits;
    private Credits credits;

    public Account(Long investorId, String investorName, Credits credits, Integer id) {
        this(
                investorId,
                investorName,
                credits,
                credits,
                new InvestorProfile(ProfileType.CONSERVATIVE, new ArrayList<>()),
                new HashMap<>(),
                id
        );
    }

    public Account(Long investorId, String investorName, Credits credits, Credits initialCredits,
                   InvestorProfile investorProfile,
                   Map<TransactionNumber, Long> remainingStocksMap, Integer id) {
        this.investorId = investorId;
        this.investorName = investorName;
        this.credits = credits;
        this.initialCredits = initialCredits;
        this.investorProfile = investorProfile;
        this.remainingStocksMap = remainingStocksMap;
        this.id = id;
    }

    public void buyTransaction(TransactionBuy transactionBuy) {
        Credits totalPrice = transactionBuy.getValueWithFees();
        if (this.credits.isSmaller(totalPrice)) {
            throw new NotEnoughCreditsException();
        }
        this.credits = this.credits.subtract(totalPrice);
        this.remainingStocksMap.put(transactionBuy.getTransactionNumber(),
                transactionBuy.getQuantity());
    }

    public void sellTransaction(TransactionSell transactionSell,
                                TransactionBuy referredTransaction) {
        if (!transactionSell.getStock().equals(referredTransaction.getStock())) {
            throw new StockParametersDontMatchException();
        }

        this.deduceStocks(referredTransaction, transactionSell.getQuantity());
        if (this.credits.isSmaller(transactionSell.getFees())) {
            throw new NotEnoughCreditsForFeesException();
        }
        this.credits =
                this.credits.subtract(transactionSell.getFees()).add(transactionSell.getValue());
    }

    private void deduceStocks(TransactionBuy transactionBuy, Long quantity) {
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

    public Integer getId() {
        return this.id;
    }

    public Credits getInitialCredits() {
        return this.initialCredits;
    }
}
