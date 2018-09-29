package domain;

import domain.investorprofile.TransactionId;

public class Transaction {
    private TransactionId transactionId;
    private TransactionType transactionType;
    private Long quantity;
    private DateTime date;
    private Stock stock;
    private Credits transactionValue;


    public Transaction(TransactionType transactionType, Long quantity, DateTime date, Stock stock, Credits transactionValue) {
        //TODO: générer le transactionId
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.date = date;
        this.stock = stock;
        this.transactionValue = transactionValue;
    }
    //calculateTransactionPrice
}
