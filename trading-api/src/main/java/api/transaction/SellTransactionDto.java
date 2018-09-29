package api.transaction;

import domain.TransactionType;
import domain.investorprofile.TransactionId;

public abstract class SellTransactionDto extends TransactionDto {
    private TransactionType type;
    private TransactionId transactionId;

    public TransactionType getType() {
        return this.type;
    }

    public void setType() {
        this.type = TransactionType.SELL;
    }
}
