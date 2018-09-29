package domain;

public class Transaction {
    private TransactionId transactionId;
    private TransactionType transactionType;
    private Long quantity;
    private DateTime date;
    private Stock stock;
    private Credits transactionValue;


    public Transaction(TransactionType transactionType, Long quantity, DateTime date, Stock stock,
                       Credits transactionValue, TransactionId transactionId) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.date = date;
        this.stock = stock;
        this.transactionValue = transactionValue;
    }

    public Credits calculateTransactionPrice() {
        return transactionValue.multiply(quantity);
    }

    public TransactionId getTransactionId() {
        return transactionId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

}
