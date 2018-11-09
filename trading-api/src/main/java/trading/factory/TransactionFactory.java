//DOIT ALLER DANS LE DOMAINE

package trading.factory;

import trading.api.request.TransactionPostRequestDTO;
import trading.domain.Credits;
import trading.domain.DateTime;
import trading.domain.Stock;
import trading.domain.transaction.*;
import trading.exception.InvalidQuantityException;
import trading.exception.NotEnoughStockException;
import trading.exception.StockParametersDontMatchException;
import trading.exception.UnsupportedTransactionTypeException;
import trading.services.StockService;
import trading.services.TransactionService;

public class TransactionFactory {

    public Transaction create(TransactionPostRequestDTO transactionPostRequestDTO, StockService stockService, TransactionService transactionService) {
        if (transactionPostRequestDTO.type.equals(TransactionType.BUY)) {

            Long quantity = transactionPostRequestDTO.quantity;
            if (quantity <= 0) {
                throw new InvalidQuantityException();
            }

            Stock stock = transactionPostRequestDTO.stock;

            DateTime dateTime = transactionPostRequestDTO.date;

            Credits stockPrice = stockService.getStockPrice(stock, dateTime);

            return new TransactionBuy(quantity, dateTime, stock, stockPrice);
        } else if (transactionPostRequestDTO.type.equals(TransactionType.SELL)) {

            Long quantity = transactionPostRequestDTO.quantity;
            if (quantity <= 0) {
                throw new InvalidQuantityException();
            }
            TransactionNumber referredTransactionNumber = transactionPostRequestDTO.transactionNumber;
            TransactionBuy referredTransaction = transactionService.getReferredTransaction(referredTransactionNumber);
            Stock stock = transactionPostRequestDTO.stock;
            if (!referredTransaction.hasEnoughStock(quantity)) {
                throw new NotEnoughStockException(stock);
            }
            if (!referredTransaction.getStock().equals(stock)) {
                throw new StockParametersDontMatchException();
            }

            DateTime dateTime = transactionPostRequestDTO.date;

            Credits stockPrice = stockService.getStockPrice(stock, dateTime);

            return new TransactionSell(quantity, dateTime, stock, stockPrice, referredTransactionNumber);
        } else {
            throw new UnsupportedTransactionTypeException(transactionPostRequestDTO.type);
        }
    }
}