package api.transaction;

import domain.Credits;
import domain.DateTime;
import domain.stock.Stock;
import domain.transaction.*;

public class TransactionDtoFactory {

    public static TransactionDto createTransaction(Transaction transaction) {
        if (transaction instanceof TransactionBuy) {
            TransactionBuyGetDto transactionBuyGetDto = new TransactionBuyGetDto();

            TransactionNumber transactionNumber = transaction.getTransactionNumber();
            TransactionType transactionType = transaction.getTransactionType();
            long quantity = transaction.getQuantity();
            DateTime dateTime = transaction.getDate();
            Stock stock = transaction.getStock();
            Credits purchasedPrice = transaction.getPrice();

            transactionBuyGetDto.setTransactionNumber(transactionNumber.getId());
            transactionBuyGetDto.setType(transactionType.toString());
            transactionBuyGetDto.setDate(dateTime.toString());
            transactionBuyGetDto.setStock(stock);
            transactionBuyGetDto.setPurchasedPrice(purchasedPrice.valueToFloat());
            transactionBuyGetDto.setQuantity(quantity);

            return transactionBuyGetDto;
        } else if (transaction instanceof TransactionSell) {
            TransactionGetSellDto transactionGetSellDto = new TransactionGetSellDto();

            TransactionNumber transactionNumber = transaction.getTransactionNumber();
            TransactionType transactionType = transaction.getTransactionType();
            long quantity = transaction.getQuantity();
            DateTime dateTime = transaction.getDate();
            Stock stock = transaction.getStock();
            Credits purchasedPrice = transaction.getPrice();
            Credits fees = transaction.getFees();

            transactionGetSellDto.setTransactionNumber(transactionNumber.getId());
            transactionGetSellDto.setType(transactionType.toString());
            transactionGetSellDto.setDate(dateTime.toString());
            transactionGetSellDto.setStock(stock);
            transactionGetSellDto.setPriceSold(purchasedPrice.valueToFloat());
            transactionGetSellDto.setQuantity(quantity);
            transactionGetSellDto.setFees(fees.valueToFloat());

            return transactionGetSellDto;
        }

        return null;
    }
}