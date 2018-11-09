package trading.domain.transaction;

import trading.domain.Account.Account;
import trading.domain.Account.AccountNumber;
import trading.domain.Credits;
import trading.domain.DateTime;
import trading.domain.Stock;
import trading.exception.NotEnoughCreditsException;

public class TransactionBuy extends Transaction {

    private Long remainingStocks;

    public TransactionBuy(
            Long quantity,
            DateTime dateTime,
            Stock stock,
            Credits stockPrice,
            AccountNumber accountNumber
    ) {
        super(quantity, dateTime, stock, stockPrice, accountNumber);
        this.transactionType = TransactionType.BUY;
        this.remainingStocks = quantity;
    }

    public void executeTransaction(Account account) {
        Credits totalPrice = this.getTotalPrice();
        if (!account.hasEnoughCreditsToPay(totalPrice)) {
            throw new NotEnoughCreditsException(this.transactionNumber);
        }
        account.subtractCredits(totalPrice);
    }

    public void deduceStock(Long soldQuantity) {
        this.remainingStocks -= soldQuantity;
    }

    public boolean hasEnoughStock(Long soldQuantity) {
        return this.remainingStocks >= soldQuantity;
    }

    public Long getRemainingStocks() {
        return this.remainingStocks;
    }

}
