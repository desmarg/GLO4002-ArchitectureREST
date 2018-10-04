package api.transaction;

import domain.transaction.Transaction;
import domain.transaction.TransactionBuy;
import domain.transaction.TransactionSell;
import exception.UnsupportedTransactionTypeException;

public class TransactionDtoFactory {

    public static TransactionDto createTransaction(Transaction transaction) {
        if (transaction instanceof TransactionBuy) {

            return new TransactionBuyGetDto(transaction);
        } else if (transaction instanceof TransactionSell) {

            return new TransactionGetSellDto(transaction);
        }

        throw new UnsupportedTransactionTypeException(transaction.getTransactionType());
    }
}