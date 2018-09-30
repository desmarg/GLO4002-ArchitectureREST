package api.transaction;

import application.StockService;
import domain.Credits;
import domain.DateTime;
import domain.stock.Stock;
import domain.transaction.Transaction;
import domain.transaction.TransactionNumber;
import domain.transaction.TransactionType;
import exception.UnsupportedTransactionTypeException;

public class TransactionPostDtoToTransactionAssembler {

    public static Transaction createTransaction(TransactionPostDto transactionPostDto) {

        Transaction transaction;
        TransactionType transactionType = transactionPostDto.getType();
        long quantity = transactionPostDto.getQuantity();
        DateTime dateTime = new DateTime(transactionPostDto.getDate());
        Stock stock = transactionPostDto.getStock();
        Credits stockPrice = StockService.getStockPrice(stock, dateTime);

        if (transactionPostDto.getType() == TransactionType.SELL) {
            TransactionNumber referredTransactionNumber = new TransactionNumber(transactionPostDto.getTransactionNumber());

            transaction = new Transaction(
                    transactionType,
                    quantity,
                    dateTime,
                    stock,
                    stockPrice,
                    referredTransactionNumber
            );
        } else if (transactionPostDto.getType() == TransactionType.BUY) {
            transaction = new Transaction(
                    transactionType,
                    quantity,
                    dateTime,
                    stock,
                    stockPrice
            );
        } else {
            throw new UnsupportedTransactionTypeException(transactionPostDto.getType());
        }
        return transaction;
    }
}
