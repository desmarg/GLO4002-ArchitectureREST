package trading.domain.transaction;

import trading.domain.Account.Account;
import trading.domain.Account.AccountNumber;
import trading.domain.Account.NotEnoughCreditsException;
import trading.domain.Credits.Credits;
import trading.domain.DateTime.DateTime;
import trading.domain.Stock;

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
        if (this.remainingStocks < soldQuantity) {
            throw new NotEnoughStockException(this.stock);
        }
        this.remainingStocks -= soldQuantity;
    }

    public boolean hasEnoughStock(Long soldQuantity) {
        return this.remainingStocks >= soldQuantity;
    }
}
