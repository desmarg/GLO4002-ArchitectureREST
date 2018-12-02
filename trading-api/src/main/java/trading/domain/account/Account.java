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
    private final ArrayList<Credits> initialCredits;
    private ArrayList<Credits> creditList;

    public Account(Long investorId, String investorName, ArrayList<Credits> creditList, Integer id) {
        this(
                investorId,
                investorName,
                creditList,
                creditList,
                new InvestorProfile(ProfileType.CONSERVATIVE, new ArrayList<>()),
                new HashMap<>(),
                new AccountNumber(investorName, id)
        );
    }

    public Account(Long investorId, String investorName, ArrayList<Credits> creditList, ArrayList<Credits> initialCredits,
                   InvestorProfile investorProfile,
                   Map<TransactionNumber, Long> remainingStocksMap, AccountNumber accountNumber) {
        this.investorId = investorId;
        this.investorName = investorName;
        this.creditList = creditList;
        this.initialCredits = initialCredits;
        this.investorProfile = investorProfile;
        this.remainingStocksMap = remainingStocksMap;
        this.accountNumber = accountNumber;
    }

    public void buyTransaction(TransactionBuy transactionBuy) {
        Credits totalPrice = transactionBuy.getValueWithFees();
//        if (this.credits.isSmaller(totalPrice)) {
//            throw new NotEnoughCreditsException();
//        }
//        this.credits = this.credits.subtract(totalPrice);
        this.remainingStocksMap.put(transactionBuy.getTransactionNumber(),
                transactionBuy.getQuantity());
    }

    public void sellTransaction(TransactionSell transactionSell,
                                TransactionBuy referredTransaction) {
        if (!transactionSell.getStock().equals(referredTransaction.getStock())) {
            throw new StockParametersDontMatchException();
        }

        this.deduceStocks(referredTransaction, transactionSell.getQuantity());
//        if (this.credits.isSmaller(transactionSell.getFees())) {
//            throw new NotEnoughCreditsForFeesException();
//        }
//        this.credits =
//                this.credits.subtract(transactionSell.getFees()).add(transactionSell.getValue());
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
        return this.accountNumber;
    }

    public InvestorProfile getInvestorProfile() {
        return this.investorProfile;
    }

    public Long getInvestorId() {
        return this.investorId;
    }

    public ArrayList<Credits> getCredits() {
        return this.creditList;
    }

    public String getInvestorName() {
        return this.investorName;
    }

    public Map<TransactionNumber, Long> getRemainingStocksMap() {
        return this.remainingStocksMap;
    }

    public ArrayList<Credits> getInitialCredits() {
        return this.initialCredits;
    }

    public BigDecimal getTotalCreditsInCAD(ForeignExchangeRepository forexRepo) {
        return forexRepo.calculateCreditSumInCAD(this.creditList);
    }
}
