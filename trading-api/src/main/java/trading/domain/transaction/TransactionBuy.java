package trading.domain.transaction;

import trading.domain.Account.AccountNumber;
import trading.domain.Credits.Credits;
import trading.domain.DateTime.DateTime;
import trading.domain.Stock;

public class TransactionBuy extends Transaction {

    public TransactionBuy(
            Long quantity,
            DateTime dateTime,
            Stock stock,
            Credits stockPrice,
            AccountNumber accountNumber
    ) {
        super(quantity, dateTime, stock, stockPrice, accountNumber);
        this.transactionType = TransactionType.BUY;
    }

    public TransactionBuy(
            Long quantity,
            DateTime dateTime,
            Stock stock,
            Credits stockPrice,
            AccountNumber accountNumber,
            TransactionNumber transactionNumber
    ) {
        super(quantity, dateTime, stock, stockPrice, accountNumber, transactionNumber);
        this.transactionType = TransactionType.BUY;
    }

    public Credits getValueWithFees() {
        Credits totalPrice = new Credits();
        totalPrice.add(this.value);
        totalPrice.add(this.fees);

        return totalPrice;
    }
}
