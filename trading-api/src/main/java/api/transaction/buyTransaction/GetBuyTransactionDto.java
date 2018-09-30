package api.transaction.buyTransaction;

import domain.Credits;
import domain.DateTime;
import domain.transaction.TransactionId;

import java.util.UUID;

public class GetBuyTransactionDto extends BuyTransactionDto {
    private DateTime date;
    private TransactionId transactionNumber;
    private Credits purchasedPrice;

    public UUID getTransactionNumber() {
        return this.transactionNumber.getId();
    }

    public void setTransactionNumber(TransactionId transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public float getPurchasedPrice() {
        return this.purchasedPrice.valueToFloat();
    }

    public void setPurchasedPrice(Credits purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }

    public String getDate() {
        return date.toString();
    }

    public void setDate(DateTime date) {
        this.date = date;
    }
}
