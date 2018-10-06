package trading.domain.transaction;

import trading.api.request.TransactionPostRequest;
import trading.domain.Account;
import trading.domain.Credits;
import trading.domain.DateTime;
import trading.domain.Stock;
import trading.exception.InvalidQuantityException;
import trading.exception.NotEnoughCreditsException;
import trading.external.api.StockExternalApi;

public class TransactionBuy extends Transaction {

    private long remainingStocks;

    public TransactionBuy(Long quantity, DateTime date, Stock stock,
                          Credits stockPrice) {
        super(quantity, date, stock, stockPrice);
        this.transactionType = TransactionType.BUY;
        this.remainingStocks = quantity;
    }

    public static Transaction fromRequest(TransactionPostRequest transactionRequest) {
        long quantity = transactionRequest.getQuantity();
        DateTime dateTime = new DateTime(transactionRequest.getDate());
        Stock stock = transactionRequest.getStock();
        //Credits stockPrice = StockService.getInstance().getStockPrice(stock, dateTime);
        Credits stockPrice = StockExternalApi.getStockPrice(stock, dateTime);
        return new TransactionBuy(
                quantity,
                dateTime,
                stock,
                stockPrice
        );
    }

    public void make(Account account) {
        Credits totalPrice = this.getTotalPrice();
        if (!account.hasEnoughCreditsToPay(totalPrice)) {
            throw new NotEnoughCreditsException(this.transactionNumber);
        }

        if (this.quantity <= 0) {
            throw new InvalidQuantityException(this.transactionNumber);
        }

        account.subtractCredits(totalPrice);
        account.addTransaction(this);
    }

    public void deduceStock(long soldQuantity) {
        this.remainingStocks -= soldQuantity;
    }

    public boolean hasEnoughStock(long soldQuantity) {
        return this.remainingStocks >= soldQuantity;
    }

    public long getRemainingStocks() {
        return this.remainingStocks;
    }

}

