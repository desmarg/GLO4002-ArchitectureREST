package trading.domain.transaction;

import trading.domain.account.AccountNumber;
import trading.domain.Credits;
import trading.domain.datetime.DateTime;
import trading.domain.Stock;

public abstract class Transaction {
    public static final Credits FEE_OVER_OR_EQ_100 = Credits.fromString("0.25");
    public static final Credits FEE_UNDER_100 = Credits.fromString("0.20");
    public static final Credits FEE_OVER_5000 = Credits.fromString("0.03");

    protected final AccountNumber accountNumber;
    protected final TransactionNumber transactionNumber;
    protected TransactionType transactionType;
    protected final Long quantity;
    protected final DateTime dateTime;
    protected final Stock stock;
    protected final Credits stockPrice;
    protected final Credits value;
    protected final Credits fees;


    protected Transaction(
            Long quantity,
            DateTime dateTime,
            Stock stock,
            Credits stockPrice,
            AccountNumber accountNumber
    ) {
        this.transactionNumber = new TransactionNumber();
        this.quantity = quantity;
        this.dateTime = dateTime;
        this.stock = stock;
        this.stockPrice = stockPrice;
        this.accountNumber = accountNumber;
        this.value = this.calculateValue();
        this.fees = this.calculateFees();
    }

    protected Transaction(
            Long quantity,
            DateTime dateTime,
            Stock stock,
            Credits stockPrice,
            AccountNumber accountNumber,
            TransactionNumber transactionNumber
    ) {
        this.transactionNumber = transactionNumber;
        this.quantity = quantity;
        this.dateTime = dateTime;
        this.stock = stock;
        this.stockPrice = stockPrice;
        this.accountNumber = accountNumber;
        this.value = this.calculateValue();
        this.fees = this.calculateFees();
    }

    private Credits calculateValue() {
        return this.stockPrice.multiply(Credits.fromLong(this.quantity));
    }

    private Credits calculateFees() {
        Credits fees = Credits.ZERO;
        if (this.quantity <= 100) {
            fees = fees.add(FEE_OVER_OR_EQ_100).multiply(Credits.fromLong(this.quantity));
        } else {
            fees = fees.add(FEE_UNDER_100).multiply(Credits.fromLong(this.quantity));
        }
        if (this.value.isGreater(Credits.fromInteger(5000))) {
            fees = fees.add(this.value.multiply(FEE_OVER_5000));
        }
        return fees;
    }

    public TransactionNumber getTransactionNumber() {
        return this.transactionNumber;
    }

    public String getStringTransactionId() {
        return this.transactionNumber.getStringUUID();
    }

    public Long getQuantity() {
        return this.quantity;
    }

    public Stock getStock() {
        return this.stock;
    }

    public Credits getStockPrice() {
        return this.stockPrice;
    }

    public DateTime getDateTime() {
        return this.dateTime;
    }

    public TransactionType getTransactionType() {
        return this.transactionType;
    }

    public Credits getValue() {
        return this.value;
    }

    public Credits getFees() {
        return this.fees;
    }

    public AccountNumber getAccountNumber() {
        return this.accountNumber;
    }

    public String getMarket() {
        return this.stock.getMarket();
    }
}
