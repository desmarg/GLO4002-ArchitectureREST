package api.transaction;

import domain.TransactionType;

public abstract class BuyTransactionDto extends TransactionDto {
    private TransactionType transactionType;

    public TransactionType getType() {
        return this.transactionType;
    }

    public void setType() {
        this.transactionType = TransactionType.BUY;
    }
}
