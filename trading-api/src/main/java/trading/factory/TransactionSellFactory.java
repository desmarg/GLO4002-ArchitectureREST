package trading.factory;

import trading.api.request.TransactionPostRequestDTO;
import trading.domain.Credits;
import trading.domain.DateTime;
import trading.domain.Stock;
import trading.domain.transaction.Transaction;
import trading.domain.transaction.TransactionBuy;
import trading.domain.transaction.TransactionNumber;
import trading.domain.transaction.TransactionSell;
import trading.exception.InvalidQuantityException;
import trading.exception.NotEnoughStockException;
import trading.exception.StockParametersDontMatchException;
import trading.services.StockService;

public class TransactionSellFactory {

    public static TransactionSell create(TransactionPostRequestDTO transactionPostRequestDTO, Transaction referredTransaction, StockService stockService) {

        Long quantity = transactionPostRequestDTO.quantity;
        if (quantity <= 0) {
            throw new InvalidQuantityException();
        }

        Stock stock = transactionPostRequestDTO.stock;
        if (!((TransactionBuy) referredTransaction).hasEnoughStock(quantity)) {
            throw new NotEnoughStockException(stock);
        }
        if (!referredTransaction.getStock().equals(stock)) {
            throw new StockParametersDontMatchException();
        }

        DateTime dateTime = transactionPostRequestDTO.date;

        Credits stockPrice = stockService.getStockPrice(stock, dateTime);

        TransactionNumber referredTransactionNumber = transactionPostRequestDTO.transactionNumber;

        return new TransactionSell(quantity, dateTime, stock, stockPrice, referredTransactionNumber);

    }
}