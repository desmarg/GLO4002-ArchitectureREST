package domain;


public class Transaction {
    private TransactionID transactionID;
    private long accountNumber;
    private TransactionType transactionType;
    private long quantity;
    private DateTime date;
    private Stock stock;


    public Transaction(TransactionID transactionID, long accountNumber, TransactionType transactionType, long quantity, DateTime date, Stock stock){
        this.transactionID = transactionID;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.date = date;
        this.stock = stock;
    }
    //calculateTransactionPrice
}
