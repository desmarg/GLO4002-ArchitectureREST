package domain;


public class Transaction {
    private TransactionId transactionID;
    private Long accountNumber;
    private TransactionType transactionType;
    private Long quantity;
    private DateTime date;
    private Stock stock;
    private Credits transactionValue;


    public Transaction(TransactionId transactionID, Long accountNumber, TransactionType transactionType, Long quantity, DateTime date, Stock stock, Credits transactionValue){
        this.transactionID = transactionID;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.date = date;
        this.stock = stock;
        this.transactionValue = transactionValue;
    }

    public Credits calculateTransactionPrice(){
        return transactionValue.multiplyByScalar(quantity);
    }
}
