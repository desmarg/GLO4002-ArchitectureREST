package domain;

public class Transaction {
    private Long accountNumber;
    private TransactionType transactionType;
    private Stock stock;
    private Long quantity;

    public void Transaction(Long accountNumber, TransactionType transactionType, Stock stock, Long quantity){
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.stock = stock;
        this.quantity = quantity;
    }
}
