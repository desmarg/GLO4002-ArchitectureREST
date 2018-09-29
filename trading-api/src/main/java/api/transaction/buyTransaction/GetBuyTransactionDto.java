package api.transaction.buyTransaction;

import domain.transaction.Transaction;

import java.util.UUID;

public class GetBuyTransactionDto extends BuyTransactionDto {
    private UUID transactionNumber;

    private Float purchasedPrice;

    public GetBuyTransactionDto(Transaction transaction) {
        this.transactionNumber = transaction.getTransactionId().getId();
        this.purchasedPrice = transaction.getStockPrice().valueToFloat();
        this.setType();
    }

    public UUID getTransactionNumber() {
        return this.transactionNumber;
    }

    public void setTransactionNumber(UUID transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public Float getPurchasedPrice() {
        return this.purchasedPrice;
    }

    public void setPurchasedPrice(Float purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }
}
