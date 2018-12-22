package trading.domain.transaction;

import trading.domain.Credits;
import trading.domain.Currency;
import trading.domain.Stock;
import trading.domain.account.AccountNumber;
import trading.domain.datetime.DateTime;

import java.math.BigDecimal;

public abstract class Transaction {
    protected static final BigDecimal FEE_UNDER_OR_EQ_100 = new BigDecimal("0.25");
    protected static final BigDecimal FEE_OVER_100 = new BigDecimal("0.20");
    protected static final BigDecimal FEE_OVER_5000 = new BigDecimal("0.03");

    private final AccountNumber accountNumber;
    private final TransactionID transactionID;
    private final Long quantity;
    private final DateTime dateTime;
    private final Stock stock;
    private final Credits stockPrice;
    protected final Currency currency;
    protected TransactionType transactionType;


    protected Transaction(Long quantity, DateTime dateTime, Stock stock, Credits stockPrice,
                          AccountNumber accountNumber) {
        this.transactionID = new TransactionID();
        this.quantity = quantity;
        this.dateTime = dateTime;
        this.stock = stock;
        this.stockPrice = stockPrice;
        this.currency = stockPrice.getCurrency();
        this.accountNumber = accountNumber;
    }

    protected Transaction(Long quantity, DateTime dateTime, Stock stock, Credits stockPrice,
                          AccountNumber accountNumber, TransactionID transactionID) {
        this.transactionID = transactionID;
        this.quantity = quantity;
        this.dateTime = dateTime;
        this.stock = stock;
        this.stockPrice = stockPrice;
        this.currency = stockPrice.getCurrency();
        this.accountNumber = accountNumber;
    }

    public Credits calculateValue() {
        return this.stockPrice.multiply(Credits.fromLong(this.quantity, this.currency));
    }

    public Credits calculateFees() {
        Credits fees = new Credits(new BigDecimal("0"), this.currency);
        if (this.quantity <= 100) {
            fees = fees.add(Credits.fromLong(this.quantity, this.currency)).multiply(FEE_UNDER_OR_EQ_100);
        } else {
            fees = fees.add(Credits.fromLong(this.quantity, this.currency)).multiply(FEE_OVER_100);
        }

        Credits value = this.calculateValue();
        if (value.isGreater(Credits.fromInteger(5000, this.currency))) {
            fees = fees.add(value.multiply(FEE_OVER_5000));
        }
        return fees;
    }

    public TransactionID getTransactionID() {
        return this.transactionID;
    }

    public String getStringTransactionId() {
        return this.transactionID.getStringUUID();
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

    public AccountNumber getAccountNumber() {
        return this.accountNumber;
    }

    public String getMarket() {
        return this.stock.getMarket();
    }
}
