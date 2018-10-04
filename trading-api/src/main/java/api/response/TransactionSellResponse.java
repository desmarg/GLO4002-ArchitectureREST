package api.response;

import domain.Transaction;

public class TransactionSellResponse extends TransactionResponse {

    private float priceSold;

    public TransactionSellResponse(Transaction transaction) {
        super(transaction);
        this.priceSold = transaction.getStockPrice().valueToFloat();
    }

    public float getPriceSold() {
        return this.priceSold;
    }

    public void setPriceSold(float priceSold) {
        this.priceSold = priceSold;
    }

}
