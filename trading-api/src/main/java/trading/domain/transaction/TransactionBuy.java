package trading.domain.transaction;

import trading.domain.Credits;
import trading.domain.Stock;
import trading.domain.account.AccountNumber;
import trading.domain.datetime.DateTime;

public class TransactionBuy extends Transaction {

    public TransactionBuy(Long quantity, DateTime dateTime, Stock stock, Credits stockPrice,
                          AccountNumber accountNumber) {
        super(quantity, dateTime, stock, stockPrice, accountNumber);
        this.transactionType = TransactionType.BUY;
    }

    public TransactionBuy(Long quantity, DateTime dateTime, Stock stock, Credits stockPrice,
                          AccountNumber accountNumber, TransactionID transactionID) {
        super(quantity, dateTime, stock, stockPrice, accountNumber, transactionID);
        this.transactionType = TransactionType.BUY;
    }

    public Credits calculateValueWithFees() {
        Credits valueWithFees = Credits.getZeroCredits(this.currency);
        return valueWithFees.add(this.calculateValue()).add(this.calculateFees());
    }
}
