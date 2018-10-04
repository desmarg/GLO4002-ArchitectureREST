package trading.factory;

import trading.api.response.TransactionBuyResponse;
import trading.api.response.TransactionResponse;
import trading.api.response.TransactionSellResponse;
import trading.domain.Transaction;
import trading.domain.TransactionBuy;
import trading.domain.TransactionSell;
import trading.exception.UnsupportedTransactionTypeException;

public class TransactionResponseFactory {

    public static TransactionResponse createTransactionDto(Transaction transaction) {
        if (transaction instanceof TransactionBuy) {
            return new TransactionBuyResponse(transaction);
        } else if (transaction instanceof TransactionSell) {
            return new TransactionSellResponse(transaction);
        }

        throw new UnsupportedTransactionTypeException(transaction.getTransactionType());
    }
}