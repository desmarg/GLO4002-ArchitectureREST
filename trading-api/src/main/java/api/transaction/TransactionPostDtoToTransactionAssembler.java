package api.transaction;

import api.request.TransactionPostRequest;
import domain.Credits;
import domain.DateTime;
import domain.stock.Stock;
import domain.transaction.*;
import exception.UnsupportedTransactionTypeException;
import services.StockService;

public class TransactionPostDtoToTransactionAssembler {

    public static Transaction createTransaction(TransactionPostRequest transactionPostDto) {

        TransactionType transactionType = TransactionType.valueOf(transactionPostDto.getType());
        long quantity = transactionPostDto.getQuantity();
        DateTime dateTime = new DateTime(transactionPostDto.getDate());
        Stock stock = transactionPostDto.getStock();
        Credits stockPrice = StockService.getInstance().getStockPrice(stock, dateTime);
        TransactionNumber referredTransactionNumber = new TransactionNumber(transactionPostDto.getTransactionNumber());

        if (transactionType == TransactionType.SELL) {
            return new TransactionSell(
                    quantity,
                    dateTime,
                    stock,
                    stockPrice,
                    referredTransactionNumber
            );
        } else if (transactionType == TransactionType.BUY) {
            return new TransactionBuy(
                    quantity,
                    dateTime,
                    stock,
                    stockPrice
            );
        }
        throw new UnsupportedTransactionTypeException(transactionType);
    }
}
