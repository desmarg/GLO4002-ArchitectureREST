package trading.domain.transaction;

import trading.domain.Account.AccountNumber;
import trading.domain.Credits.Credits;
import trading.domain.DateTime.DateTime;
import trading.domain.Stock;

public abstract class Transaction {
    protected AccountNumber accountNumber;
    protected TransactionNumber transactionNumber;
    protected TransactionType transactionType;
    protected Long quantity;
    protected DateTime dateTime;
    protected Stock stock;
    protected Credits stockPrice;
    protected Credits price;
    protected Credits fees;


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
        this.price = this.calculateTransactionPrice();
        this.fees = this.calculateFees();
    }

    public Credits calculateTransactionPrice() {
        Credits transactionPrice = new Credits(this.stockPrice);
        transactionPrice.multiply(this.quantity);

        return transactionPrice;
    }

    public Credits getTotalPrice() {
        Credits totalPrice = new Credits();
        totalPrice.add(this.price);
        totalPrice.add(this.fees);

        return totalPrice;
    }

    public Credits calculateFees() {
        Credits fees = new Credits();
        if (this.quantity <= 100) {
            double feeFor100orMoreTransactions = 0.25;
            Credits baseFee = Credits.fromDouble(feeFor100orMoreTransactions);
            fees.add(baseFee);
            fees.multiply(this.quantity);
        } else {
            double feeUnder100Transactions = 0.20;
            Credits baseFee = Credits.fromDouble(feeUnder100Transactions);
            fees.add(baseFee);
            fees.multiply(this.quantity);
        }
        if (this.price.compareTo(Credits.fromDouble(5000)) > 0) {
            Credits additionalFees = new Credits(this.price);
            double additionalPercentFeeOver5000 = 0.03;
            additionalFees.multiply(additionalPercentFeeOver5000);
            fees.add(additionalFees);
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

    public Credits getPrice() {
        return this.price;
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
