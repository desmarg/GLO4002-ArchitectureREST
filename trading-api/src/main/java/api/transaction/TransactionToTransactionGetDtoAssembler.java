package api.transaction;

import domain.Credits;
import domain.DateTime;
import domain.stock.Stock;
import domain.transaction.Transaction;
import domain.transaction.TransactionBuy;
import domain.transaction.TransactionNumber;
import domain.transaction.TransactionType;

public class TransactionToTransactionGetDtoAssembler {

    public static TransactionGetDto createTransactionGetDto(TransactionBuy transactionBuy) {

        TransactionGetDto transactionGetDto = new TransactionGetDto();

        TransactionNumber transactionNumber = transactionBuy.getTransactionNumber();
        TransactionType transactionType = transactionBuy.getTransactionType();
        long quantity = transactionBuy.getQuantity();
        DateTime dateTime = transactionBuy.getDate();
        Stock stock = transactionBuy.getStock();
        Credits purchasedPrice = transactionBuy.getStockPrice();

        transactionGetDto.setTransactionNumber(transactionNumber.getId());
        transactionGetDto.setType(transactionType.toString());
        transactionGetDto.setDate(dateTime.toString());
        transactionGetDto.setStock(stock);
        transactionGetDto.setPrice(purchasedPrice.valueToFloat());
        transactionGetDto.setQuantity(quantity);

        return transactionGetDto;
    }

    public static TransactionGetDto createTransactionGetDto(Transaction transaction) {

        TransactionGetDto transactionGetDto = new TransactionGetDto();

        TransactionNumber transactionNumber = transaction.getTransactionNumber();
        TransactionType transactionType = transaction.getTransactionType();
        long quantity = transaction.getQuantity();
        DateTime dateTime = transaction.getDate();
        Stock stock = transaction.getStock();
        Credits purchasedPrice = transaction.getStockPrice();

        transactionGetDto.setTransactionNumber(transactionNumber.getId());
        transactionGetDto.setType(transactionType.toString());
        transactionGetDto.setDate(dateTime.toString());
        transactionGetDto.setStock(stock);
        transactionGetDto.setPrice(purchasedPrice.valueToFloat());
        transactionGetDto.setQuantity(quantity);

        return transactionGetDto;
    }
}
