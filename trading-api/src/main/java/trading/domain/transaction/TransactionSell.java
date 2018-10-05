package trading.domain.transaction;

import trading.api.request.TransactionPostRequest;
import trading.domain.Account;
import trading.domain.Credits;
import trading.domain.DateTime;
import trading.domain.Stock;
import trading.exception.InvalidQuantityException;
import trading.exception.NotEnoughStockException;
import trading.exception.StockParametersDontMatchException;
import trading.services.StockService;

public class TransactionSell extends Transaction {

    private TransactionNumber referredTransactionNumber;

    public TransactionSell(Long quantity, DateTime date, Stock stock,
                           Credits stockPrice, TransactionNumber referredTransactionNumber) {
        super(quantity, date, stock, stockPrice);
        this.transactionType = TransactionType.SELL;
        this.referredTransactionNumber = referredTransactionNumber;
    }

    public static TransactionSell fromRequest(TransactionPostRequest transactionRequest) {
        long quantity = transactionRequest.getQuantity();
        DateTime dateTime = new DateTime(transactionRequest.getDate());
        Stock stock = transactionRequest.getStock();
        Credits stockPrice = StockService.getInstance().getStockPrice(stock, dateTime);
        TransactionNumber referredTransactionNumber = new TransactionNumber(transactionRequest.getTransactionNumber());
        return new TransactionSell(
                quantity,
                dateTime,
                stock,
                stockPrice,
                referredTransactionNumber
        );
    }

    public void make(Account account) {
        account.addTransaction(this);
        TransactionBuy referredTransaction = (TransactionBuy) account.getTransaction(this.referredTransactionNumber);

        if (this.getQuantity() <= 0) {
            throw new InvalidQuantityException(this.transactionNumber);
        }

        if (!referredTransaction.getStock().equals(this.getStock())) {
            throw new StockParametersDontMatchException(this.transactionNumber);
        }

        if (!referredTransaction.hasEnoughStock(this.quantity)) {
            throw new NotEnoughStockException(this.stock, this.transactionNumber);
        }

        referredTransaction.deduceStock(this.quantity);
        account.addCredits(this.price);
        account.substractCredits(this.fees);

    }

    public TransactionNumber getReferredTransactionNumber() {
        return this.referredTransactionNumber;
    }
}
