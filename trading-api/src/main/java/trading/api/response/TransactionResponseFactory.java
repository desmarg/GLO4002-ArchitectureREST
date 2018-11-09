package trading.api.response;

import trading.domain.transaction.Transaction;
import trading.domain.transaction.TransactionBuy;
import trading.domain.transaction.TransactionSell;
import trading.exception.UnsupportedTransactionTypeException;

public class TransactionResponseFactory {
    public static TransactionResponse createTransactionDto(Transaction transaction) {
        if (transaction instanceof TransactionBuy) {
            return new TransactionBuyResponse(transaction);
        } else if (transaction instanceof TransactionSell) {
            return new TransactionSellResponse(transaction);
        }

        throw new UnsupportedTransactionTypeException(transaction.getTransactionType().toString());
    }
}
