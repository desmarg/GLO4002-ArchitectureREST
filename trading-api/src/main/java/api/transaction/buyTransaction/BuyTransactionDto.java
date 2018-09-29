package api.transaction.buyTransaction;

import api.transaction.TransactionDto;
import domain.TransactionType;

public abstract class BuyTransactionDto extends TransactionDto {
    private TransactionType type;

    public TransactionType getType() {
        return this.type;
    }

    public void setTransactionType() {
        this.type = TransactionType.BUY;
    }
}
