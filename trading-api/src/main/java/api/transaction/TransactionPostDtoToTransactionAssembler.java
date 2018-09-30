package api.transaction;

import application.StockService;
import domain.Credits;
import domain.DateTime;
import domain.stock.Stock;
import domain.transaction.Transaction;
import domain.transaction.TransactionNumber;
import domain.transaction.TransactionType;

public class TransactionPostDtoToTransactionAssembler {

    public static Transaction createTransaction(TransactionPostDto transactionPostDto) {

        Transaction transaction;
        TransactionType transactionType = TransactionType.getType(transactionPostDto.getType());
        long quantity = transactionPostDto.getQuantity();
        DateTime dateTime = new DateTime(transactionPostDto.getDate());
        Stock stock = transactionPostDto.getStock();
        Credits stockPrice = StockService.getInstance().getStockPrice(stock, dateTime);
        TransactionNumber referredTransactionNumber = new TransactionNumber(transactionPostDto.getTransactionNumber());

        transaction = new Transaction(
                transactionType,
                quantity,
                dateTime,
                stock,
                stockPrice,
                referredTransactionNumber
        );

        return transaction;
    }
}
