package trading.domain.transaction;

import trading.domain.Account.Account;
import trading.domain.Account.AccountNumber;
import trading.domain.Credits.Credits;
import trading.domain.DateTime.DateTime;
import trading.domain.Stock;

public class TransactionSell extends Transaction {
    private TransactionNumber referredTransactionNumber;

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

    public void executeTransaction(Account account, TransactionBuy referredTransaction) {
        referredTransaction.deduceStock(this.quantity);
        account.addCredits(this.price);
        account.subtractCredits(this.fees);
    }

    public TransactionNumber getReferredTransactionNumber() {
        return this.referredTransactionNumber;
    }
}
