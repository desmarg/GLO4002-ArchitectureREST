package trading.domain.transaction;

import trading.domain.Credits;
import trading.domain.DateTime;
import trading.domain.Stock;

public abstract class Transaction {
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
            Credits stockPrice
    ) {
        this.transactionNumber = new TransactionNumber();
        this.quantity = quantity;
        this.dateTime = dateTime;
        this.stock = stock;
        this.stockPrice = stockPrice;
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
            Credits baseFee = Credits.fromDouble(0.25);
            fees.add(baseFee);
            fees.multiply(this.quantity);
        } else {
            Credits baseFee = Credits.fromDouble(0.20);
            fees.add(baseFee);
            fees.multiply(this.quantity);
        }
        if (this.price.compareTo(Credits.fromDouble(5000)) > 0) {
            Credits additionalFees = new Credits(this.price);
            additionalFees.multiply(0.03);
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
}
