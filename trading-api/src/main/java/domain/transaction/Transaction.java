package domain.transaction;

import domain.Credits;
import domain.DateTime;
import domain.account.Account;
import domain.stock.Stock;

import static domain.Credits.fromDouble;

public abstract class Transaction {
    protected TransactionNumber transactionNumber;
    protected TransactionType transactionType;
    protected Long quantity;
    protected DateTime date;
    protected Stock stock;
    protected Credits stockPrice;
    protected Credits price;
    protected Credits fees;


    protected Transaction(Long quantity, DateTime date, Stock stock,
                          Credits stockPrice) {
        this.transactionNumber = new TransactionNumber();
        this.quantity = quantity;
        this.date = date;
        this.stock = stock;
        this.stockPrice = stockPrice;
        this.price = this.calculateTransactionPrice();
        this.fees = this.calculateFees();
    }

    public abstract void make(Account account);

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
            Credits baseFee = fromDouble(0.25);
            fees.add(baseFee);
            fees.multiply(this.quantity);
        } else {
            Credits baseFee = fromDouble(0.20);
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

    public DateTime getDate() {
        return this.date;
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
