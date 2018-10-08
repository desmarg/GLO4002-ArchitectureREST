package trading.domain.transaction;

import trading.api.request.TransactionPostRequest;
import trading.domain.Account;
import trading.domain.Credits;
import trading.domain.DateTime;
import trading.domain.Stock;
import trading.exception.InvalidQuantityException;
import trading.exception.NotEnoughCreditsException;
import trading.services.StockService;

public class TransactionBuy extends Transaction {

    private Long remainingStocks;

    public TransactionBuy(Long quantity, DateTime dateTime, Stock stock,
                          Credits stockPrice) {
        super(quantity, dateTime, stock, stockPrice);
        this.transactionType = TransactionType.BUY;
        this.remainingStocks = quantity;
    }

    public static Transaction fromRequest(TransactionPostRequest transactionRequest) {
        Long quantity = transactionRequest.getQuantity();
        DateTime dateTime = transactionRequest.getDate();
        Stock stock = transactionRequest.getStock();
        Credits stockPrice = StockService.getInstance().getStockPrice(stock, dateTime);
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

    public void deduceStock(Long soldQuantity) {
        this.remainingStocks -= soldQuantity;
    }

    public boolean hasEnoughStock(Long soldQuantity) {
        return this.remainingStocks >= soldQuantity;
    }

    public Long getRemainingStocks() {
        return this.remainingStocks;
    }
}
