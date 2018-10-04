package api.transaction;

import api.response.TransactionBuyResponse;
import api.response.TransactionResponse;
import api.response.TransactionSellResponse;
import domain.Transaction;
import domain.TransactionBuy;
import domain.TransactionSell;
import exception.UnsupportedTransactionTypeException;

public class TransactionDtoFactory {

    public static TransactionResponse createTransactionDto(Transaction transaction) {
        if (transaction instanceof TransactionBuy) {
            return new TransactionBuyResponse(transaction);
        } else if (transaction instanceof TransactionSell) {
            return new TransactionSellResponse(transaction);
        }

        throw new UnsupportedTransactionTypeException(transaction.getTransactionType());
    }
}