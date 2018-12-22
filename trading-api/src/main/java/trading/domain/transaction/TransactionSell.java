package trading.domain.transaction;

import trading.domain.Credits;
import trading.domain.Stock;
import trading.domain.account.AccountNumber;
import trading.domain.datetime.DateTime;

public class TransactionSell extends Transaction {
    private final TransactionID referredTransactionID;

    public TransactionSell(Long quantity, DateTime dateTime, Stock stock, Credits stockPrice,
                           TransactionID referredTransactionID,
                           AccountNumber accountNumber) {
        super(quantity, dateTime, stock, stockPrice, accountNumber);
        this.transactionType = TransactionType.SELL;
        this.referredTransactionID = referredTransactionID;
    }

    public TransactionSell(Long quantity, DateTime dateTime, Stock stock, Credits stockPrice,
                           TransactionID referredTransactionID,
                           AccountNumber accountNumber, TransactionID transactionID) {
        super(quantity, dateTime, stock, stockPrice, accountNumber, transactionID);
        this.transactionType = TransactionType.SELL;
        this.referredTransactionID = referredTransactionID;
    }

    public TransactionID getReferredTransactionID() {
        return this.referredTransactionID;
    }
}
