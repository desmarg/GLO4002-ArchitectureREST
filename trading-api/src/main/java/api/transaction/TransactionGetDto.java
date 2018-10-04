package api.transaction;

public class TransactionGetDto extends TransactionDto {

    private float price;

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
