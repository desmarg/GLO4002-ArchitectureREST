package trading.domain.Transaction;

import trading.domain.Account.AccountNumber;
import trading.domain.Credits.Credits;
import trading.domain.DateTime.DateTime;
import trading.domain.Stock;

public class TransactionBuy extends Transaction {

    //TODO will be in wallet
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

    public void deduceStock(Long soldQuantity) {
        if (this.remainingStocks < soldQuantity) {
            throw new NotEnoughStockException(this.stock);
        }
        this.remainingStocks -= soldQuantity;
    }

    public boolean hasEnoughStock(Long soldQuantity) {
        return this.remainingStocks >= soldQuantity;
    }

    public Long getRemainingStocks() {
        return this.remainingStocks;
    }

    public Credits getPriceWithFees() {
        Credits totalPrice = new Credits();
        totalPrice.add(this.price);
        totalPrice.add(this.fees);

        return totalPrice;
    }
}
