package domain;

public class Transaction {
    private Long accountNumber;
    private TransactionType transactionType;
    private Long quantity;

    public void Transaction(Long accountNumber, TransactionType transactionType, Long quantity){
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.quantity = quantity;
    }
}
