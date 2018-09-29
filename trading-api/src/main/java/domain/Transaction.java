package domain;


public class Transaction {
    private TransactionId transactionID;
    private Long accountNumber;
    private TransactionType transactionType;
    private Long quantity;
    private DateTime date;
    private Stock stock;


    public Transaction(TransactionId transactionID, Long accountNumber, TransactionType transactionType, Long quantity, DateTime date, Stock stock){
        this.transactionID = transactionID;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.date = date;
        this.stock = stock;
    }
    //calculateTransactionPrice
    public float calculateTransactionPrice(){
        //TODO stock.getPrice();
        float stockPrice = 1;
        return quantity * stockPrice;
    }
}
