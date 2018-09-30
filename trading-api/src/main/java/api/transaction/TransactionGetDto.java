package api.transaction;

import api.transaction.TransactionDto;
import domain.Credits;
import domain.DateTime;
import domain.transaction.TransactionNumber;

import java.util.UUID;

public class TransactionGetDto extends TransactionDto {

    private UUID transactionNumber;
    private float purchasedPrice;

    public UUID getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(UUID transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public float getPurchasedPrice() {
        return purchasedPrice;
    }

    public void setPurchasedPrice(float purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }
}
