package api.response;

import domain.transaction.Transaction;

public class TransactionBuyResponse extends TransactionResponse {

    private float purchasedPrice;

    public TransactionBuyResponse(Transaction transaction) {
        super(transaction);
        this.setPurchasedPrice(transaction.getPrice().valueToFloat());
    }

    public float getPurchasedPrice() {
        return this.purchasedPrice;
    }

    public void setPurchasedPrice(float purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }
}
