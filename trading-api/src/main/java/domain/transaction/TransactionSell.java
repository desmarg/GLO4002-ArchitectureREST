package domain.transaction;

import domain.Credits;
import domain.DateTime;
import domain.account.Account;
import domain.stock.Stock;
import exception.NotEnoughStockException;

public class TransactionSell extends Transaction {

    private TransactionNumber referredTransactionNumber;

    public TransactionSell(Long quantity, DateTime date, Stock stock,
                           Credits stockPrice, TransactionNumber referredTransactionNumber) {
        super(quantity, date, stock, stockPrice);
        this.transactionType = TransactionType.SELL;
        this.referredTransactionNumber = referredTransactionNumber;
    }

    public void make(Account account) {
        account.addTransaction(this);
        TransactionBuy referredTransaction = (TransactionBuy) account.getTransaction(this.referredTransactionNumber);

        if (!referredTransaction.hasEnoughStock(this.quantity)) {
            throw new NotEnoughStockException(this.stock, this.transactionNumber);
        }

        referredTransaction.deduceStock(this.quantity);
        account.addCredits(this.price);
        account.substractCredits(this.fees);

    }

    public TransactionNumber getReferredTransactionNumber() {
        return this.referredTransactionNumber;
    }
}
