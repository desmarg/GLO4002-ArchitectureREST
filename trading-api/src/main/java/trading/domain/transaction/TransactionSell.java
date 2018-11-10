package trading.domain.transaction;

import trading.domain.Account.AccountNumber;
import trading.domain.Credits.Credits;
import trading.domain.DateTime.DateTime;
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

    public TransactionNumber getReferredTransactionNumber() {
        return this.referredTransactionNumber;
    }
}
