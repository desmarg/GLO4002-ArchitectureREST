package api.transaction;

import domain.TransactionType;

public abstract class BuyTransactionDto extends TransactionDto {
    private TransactionType type;

    public TransactionType getType() {
        return this.type;
    }

    public void setType() {
        this.type = TransactionType.BUY;
    }
}
