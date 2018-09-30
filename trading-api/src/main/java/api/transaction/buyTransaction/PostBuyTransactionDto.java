package api.transaction.buyTransaction;

import domain.DateTime;

public class PostBuyTransactionDto extends BuyTransactionDto {
    private DateTime date;

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }
}
