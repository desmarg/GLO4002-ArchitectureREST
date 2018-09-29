package api.transaction.buyTransaction;

import domain.investorprofile.TransactionId;

public class GetBuyTransactionDto extends BuyTransactionDto {
    private TransactionId transactionId;
    //TODO: Changer pour credits
    private Float purchasedPrice;

    public TransactionId getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(TransactionId transactionId) {
        this.transactionId = transactionId;
    }

    public Float getPurchasedPrice() {
        return this.purchasedPrice;
    }

    public void setPurchasedPrice(Float purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }
}
