package trading.domain.Wallet;

import trading.domain.Credits.Credits;
import trading.domain.Transaction.TransactionBuy;
import trading.domain.Transaction.TransactionNumber;

public class TransactionSnapshot {

    public final TransactionNumber transactionBuyNumber;
    public final Long remainingTransactionBuyStocks;
    public final Credits priceOfStockAtEndOfDay;

    public TransactionSnapshot(TransactionBuy transactionBuy) {
        this.transactionBuyNumber = transactionBuy.getTransactionNumber();
        this.remainingTransactionBuyStocks = transactionBuy.getRemainingStocks();
        this.priceOfStockAtEndOfDay = transactionBuy.getStockPrice();
    }
}
