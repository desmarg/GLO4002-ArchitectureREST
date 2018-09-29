package api.transaction.buyTransaction;

import api.transaction.TransactionDto;
import domain.TransactionType;

public abstract class BuyTransactionDto extends TransactionDto {
    private TransactionType transactionType;

    public TransactionType getTransactionTypeType() {
        return this.transactionType;
    }

    public void setTransactionTypeType() {
        this.transactionType = TransactionType.BUY;
    }
}
