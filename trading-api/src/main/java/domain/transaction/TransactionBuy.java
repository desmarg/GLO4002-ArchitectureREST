package domain.transaction;

import domain.Credits;
import domain.DateTime;
import domain.account.Account;
import domain.stock.Stock;
import exception.InvalidQuantityException;
import exception.NotEnoughCreditsException;

public class TransactionBuy extends Transaction {

    private long remainingStocks;

    public TransactionBuy(Long quantity, DateTime date, Stock stock,
                          Credits stockPrice) {
        super(quantity, date, stock, stockPrice);
        this.transactionType = TransactionType.BUY;
        this.remainingStocks = quantity;
    }

    public void make(Account account) {
        account.addTransaction(this);
        Credits transactionPrice = this.calculateTransactionPrice();
        if (!account.hasEnoughCredits(transactionPrice)) {
            throw new NotEnoughCreditsException(this.transactionNumber);
        }

        if (this.quantity <= 0) {
            throw new InvalidQuantityException(this.transactionNumber);
        }

        account.substractCredits(transactionPrice);
    }

    public void deduceStock(long soldQuantity) {
        this.remainingStocks -= soldQuantity;
    }

    public boolean hasEnoughStock(long soldQuantity) {
        return this.remainingStocks >= soldQuantity;
    }

    public long getRemainingStocks() {
        return this.remainingStocks;
    }
}

