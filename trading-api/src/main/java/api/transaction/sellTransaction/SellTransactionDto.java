package api.transaction.sellTransaction;

import api.transaction.TransactionDto;
import domain.TransactionId;
import domain.TransactionType;

public abstract class SellTransactionDto extends TransactionDto {
    private TransactionType transactionType;
    private TransactionId transactionId;

    public TransactionType getTransactionTypeType() {
        return this.transactionType;
    }

    public void setTransactionTypeType() {
        this.transactionType = TransactionType.SELL;
    }
}
