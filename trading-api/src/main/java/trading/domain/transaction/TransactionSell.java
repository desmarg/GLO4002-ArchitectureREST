package trading.domain.transaction;

import trading.domain.account.AccountNumber;
import trading.domain.Credits;
import trading.domain.datetime.DateTime;
import trading.domain.Stock;

public class TransactionSell extends Transaction {
    private final TransactionNumber referredTransactionNumber;

    public TransactionSell(
            Long quantity,
            DateTime dateTime,
            Stock stock,
            Credits stockPrice,
            TransactionNumber referredTransactionNumber,
            AccountNumber accountNumber
    ) {
        super(quantity, dateTime, stock, stockPrice, accountNumber);
        this.transactionType = TransactionType.SELL;
        this.referredTransactionNumber = referredTransactionNumber;
    }

    public TransactionSell(
            Long quantity,
            DateTime dateTime,
            Stock stock,
            Credits stockPrice,
            TransactionNumber referredTransactionNumber,
            AccountNumber accountNumber,
            TransactionNumber transactionNumber
    ) {
        super(quantity, dateTime, stock, stockPrice, accountNumber, transactionNumber);
        this.transactionType = TransactionType.SELL;
        this.referredTransactionNumber = referredTransactionNumber;
    }

    public TransactionNumber getReferredTransactionNumber() {
        return this.referredTransactionNumber;
    }
}
