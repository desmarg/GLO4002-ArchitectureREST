package api.transaction.buyTransaction;

import api.transaction.TransactionDto;
import domain.transaction.TransactionType;

public abstract class BuyTransactionDto extends TransactionDto {
    private String type;

    public String getType() {
        return this.type;
    }

    public void setType() {
        this.type = TransactionType.BUY.toString();
    }
}
