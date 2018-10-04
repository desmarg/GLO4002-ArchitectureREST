package api.transaction;

public class TransactionGetSellDto extends TransactionDto {

    private float priceSold;
    private float fees;

    public float getPriceSold() {
        return this.priceSold;
    }

    public void setPriceSold(float priceSold) {
        this.priceSold = priceSold;
    }

    public float getFees() {
        return this.fees;
    }

    public void setFees(float fees) {
        this.fees = fees;
    }
}
