package api.transaction.buyTransaction;

import api.transaction.TransactionDto;
import domain.transaction.TransactionType;

public abstract class BuyTransactionDto extends TransactionDto {
    private TransactionType type;

    public String getType() {
        return this.type.BUY.toString();
    }

    public void setType(TransactionType transactionType) {
        this.type = transactionType;
    }
}
