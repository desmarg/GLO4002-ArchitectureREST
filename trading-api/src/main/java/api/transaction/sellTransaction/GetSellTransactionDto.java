package api.transaction.sellTransaction;

public class GetSellTransactionDto extends SellTransactionDto {
    //TODO: Change to credit
    private Float priceSold;
    private Float fees;

    public Float getPriceSold() {
        return this.priceSold;
    }

    public void setPriceSold(Float priceSold) {
        this.priceSold = priceSold;
    }

    public Float getFees() {
        return this.fees;
    }

    public void setFees(Float fees) {
        this.fees = fees;
    }
}
