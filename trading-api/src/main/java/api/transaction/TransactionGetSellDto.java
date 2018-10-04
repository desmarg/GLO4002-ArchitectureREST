package api.transaction;

import domain.transaction.Transaction;

public class TransactionGetSellDto extends TransactionDto {

    private float priceSold;

    public TransactionGetSellDto(Transaction transaction) {
        super();
        this.priceSold = transaction.getStockPrice().valueToFloat();
    }

    public float getPriceSold() {
        return this.priceSold;
    }

    public void setPriceSold(float priceSold) {
        this.priceSold = priceSold;
    }

}
