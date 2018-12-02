package trading.domain.transaction;

import trading.domain.Credits;
import trading.domain.Currency;
import trading.domain.Stock;
import trading.domain.account.AccountNumber;
import trading.domain.datetime.DateTime;

import java.math.BigDecimal;

public abstract class Transaction {
    public static final BigDecimal FEE_UNDER_OR_EQ_100 = new BigDecimal("0.25");
    public static final BigDecimal FEE_OVER_100 = new BigDecimal("0.20");
    public static final BigDecimal FEE_OVER_5000 = new BigDecimal("0.03");

    protected final AccountNumber accountNumber;
    protected final TransactionNumber transactionNumber;
    protected final Long quantity;
    protected final DateTime dateTime;
    protected final Stock stock;
    protected final Credits stockPrice;
    protected final Credits value;
    protected final Credits fees;
    protected final Currency currency;
    protected TransactionType transactionType;


    protected Transaction(Long quantity, DateTime dateTime, Stock stock, Credits stockPrice,
                          AccountNumber accountNumber) {
        this.transactionNumber = new TransactionNumber();
        this.quantity = quantity;
        this.dateTime = dateTime;
        this.stock = stock;
        this.stockPrice = stockPrice;
        this.currency = stockPrice.getCurrency();
        this.accountNumber = accountNumber;
        this.value = this.calculateValue();
        this.fees = this.calculateFees();
    }

    protected Transaction(Long quantity, DateTime dateTime, Stock stock, Credits stockPrice,
                          AccountNumber accountNumber, TransactionNumber transactionNumber) {
        this.transactionNumber = transactionNumber;
        this.quantity = quantity;
        this.dateTime = dateTime;
        this.stock = stock;
        this.stockPrice = stockPrice;
        this.currency = stockPrice.getCurrency();
        this.accountNumber = accountNumber;
        this.value = this.calculateValue();
        this.fees = this.calculateFees();
    }

    private Credits calculateValue() {
        return this.stockPrice.multiply(Credits.fromLong(this.quantity, this.currency));
    }

    private Credits calculateFees() {
        Credits fees = new Credits(new BigDecimal("0"), this.currency);
        if (this.quantity <= 100) {
            fees = fees.add(Credits.fromLong(this.quantity, this.currency)).multiply(FEE_UNDER_OR_EQ_100);
        } else {
            fees = fees.add(Credits.fromLong(this.quantity, this.currency)).multiply(FEE_OVER_100);
        }
        if (this.value.isGreater(Credits.fromInteger(5000, this.currency))) {
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
