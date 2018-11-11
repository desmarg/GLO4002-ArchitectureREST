package trading.api.response;

import trading.domain.transaction.Transaction;

public class TransactionBuyResponse extends TransactionResponse {
    private Float purchasedPrice;

    public TransactionBuyResponse(Transaction transaction) {
        super(transaction);
        this.setPurchasedPrice(transaction.getValue().valueToFloat());
    }

    public Float getPurchasedPrice() {
        return this.purchasedPrice;
    }

    public void setPurchasedPrice(Float purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }
}
