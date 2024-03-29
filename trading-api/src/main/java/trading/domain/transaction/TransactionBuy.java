package trading.domain.transaction;

import trading.domain.Credits;
import trading.domain.Stock;
import trading.domain.account.AccountNumber;
import trading.domain.datetime.DateTime;

import java.math.BigDecimal;

public class TransactionBuy extends Transaction {

    public TransactionBuy(Long quantity, DateTime dateTime, Stock stock, Credits stockPrice,
                          AccountNumber accountNumber) {
        super(quantity, dateTime, stock, stockPrice, accountNumber);
        this.transactionType = TransactionType.BUY;
    }

    public TransactionBuy(Long quantity, DateTime dateTime, Stock stock, Credits stockPrice,
                          AccountNumber accountNumber, TransactionNumber transactionNumber) {
        super(quantity, dateTime, stock, stockPrice, accountNumber, transactionNumber);
        this.transactionType = TransactionType.BUY;
    }

    public Credits getValueWithFees() {
        Credits valueWithFees = Credits.getZeroCredits(this.currency);
        return valueWithFees.add(this.value).add(this.fees);
    }
}
