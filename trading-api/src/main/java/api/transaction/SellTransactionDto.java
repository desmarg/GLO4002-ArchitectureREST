package api.transaction;

import domain.TransactionType;
import domain.investorprofile.TransactionId;

public abstract class SellTransactionDto extends TransactionDto {
    private TransactionType transactionType;
    private TransactionId transactionId;

    public TransactionType getType() {
        return this.transactionType;
    }

    public void setType() {
        this.transactionType = TransactionType.SELL;
    }
}
