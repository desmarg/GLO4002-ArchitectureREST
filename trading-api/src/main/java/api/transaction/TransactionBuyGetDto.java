package api.transaction;

import domain.transaction.Transaction;

public class TransactionBuyGetDto extends TransactionDto {

    private float purchasedPrice;

    public TransactionBuyGetDto(Transaction transaction) {
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
