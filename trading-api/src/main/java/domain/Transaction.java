package domain;

import domain.investorprofile.TransactionId;

public class Transaction {
    private TransactionId transactionId;
    private Long accountNumber;
    private TransactionType transactionType;
    private Long quantity;
    private DateTime date;
    private Stock stock;
    private Credits price;


    public Transaction(TransactionId transactionId, long accountNumber, TransactionType transactionType, long quantity, DateTime date, Stock stock, Credits price) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.date = date;
        this.stock = stock;
        this.price = price;
    }

    public Credits calculateTransactionPrice(){
        //TODO stock.getPrice();
        Credits credit = Credits.fromFloat(180);
        credit.multiply(quantity);
        return credit;
    }
}
