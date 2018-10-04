package api.transaction;

public class TransactionBuyGetDto extends TransactionDto {

    private float purchasedPrice;

    public float getPurchasedPrice() {
        return this.purchasedPrice;
    }

    public void setPurchasedPrice(float purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }
}
