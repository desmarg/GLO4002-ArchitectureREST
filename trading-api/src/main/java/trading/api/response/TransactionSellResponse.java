package trading.api.response;

import trading.domain.transaction.Transaction;

public class TransactionSellResponse extends TransactionResponse {

    private Float priceSold;

    public TransactionSellResponse(Transaction transaction) {
        super(transaction);
        this.priceSold = transaction.getStockPrice().valueToFloat();
    }

    public Float getPriceSold() {
        return this.priceSold;
    }

    public void setPriceSold(Float priceSold) {
        this.priceSold = priceSold;
    }
}
