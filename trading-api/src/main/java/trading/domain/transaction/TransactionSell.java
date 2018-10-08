package trading.domain.transaction;

import trading.api.request.TransactionPostRequest;
import trading.domain.Account;
import trading.domain.Credits;
import trading.domain.DateTime;
import trading.domain.Stock;
import trading.exception.InvalidQuantityException;
import trading.exception.InvalidTransactionNumberException;
import trading.exception.NotEnoughCreditsForFeesException;
import trading.exception.NotEnoughStockException;
import trading.exception.StockParametersDontMatchException;
import trading.exception.TransactionNotFoundException;
import trading.services.StockService;

public class TransactionSell extends Transaction {
    private TransactionNumber referredTransactionNumber;

    public TransactionSell(
            Long quantity,
            DateTime dateTime,
            Stock stock,
            Credits stockPrice,
            TransactionNumber referredTransactionNumber
    ) {
        super(quantity, dateTime, stock, stockPrice);
        this.transactionType = TransactionType.SELL;
        this.referredTransactionNumber = referredTransactionNumber;
    }

    public static TransactionSell fromRequest(TransactionPostRequest transactionPostRequest) {
        Long quantity = transactionPostRequest.getQuantity();
        DateTime dateTime = transactionPostRequest.getDate();
        Stock stock = transactionPostRequest.getStock();
        Credits stockPrice = StockService.getInstance().getStockPrice(stock, dateTime);
        TransactionNumber referredTransactionNumber = transactionPostRequest.getTransactionNumber();

        return new TransactionSell(quantity, dateTime, stock, stockPrice, referredTransactionNumber);
    }

    public void make(Account account) {
        try {
            TransactionBuy referredTransaction = (TransactionBuy) account.getTransaction(
                    this.referredTransactionNumber
            );

            if (this.getQuantity() <= 0) {
                throw new InvalidQuantityException(this.referredTransactionNumber);
            }

            if (!referredTransaction.getStock().equals(this.getStock())) {
                throw new StockParametersDontMatchException(this.transactionNumber);
            }

            if (!referredTransaction.hasEnoughStock(this.quantity)) {
                throw new NotEnoughStockException(this.stock, this.transactionNumber);
            }

            if (!account.hasEnoughCreditsToPaySellFees(this.price, this.fees)) {
                throw new NotEnoughCreditsForFeesException(this.transactionNumber);
            }

            referredTransaction.deduceStock(this.quantity);
            account.addCredits(this.price);
            account.subtractCredits(this.fees);
            account.addTransaction(this);

        } catch (TransactionNotFoundException e) {
            throw new InvalidTransactionNumberException(this.transactionNumber);
        }
    }

    public TransactionNumber getReferredTransactionNumber() {
        return this.referredTransactionNumber;
    }
}
