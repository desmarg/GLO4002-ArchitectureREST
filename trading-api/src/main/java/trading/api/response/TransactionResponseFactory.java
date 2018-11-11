package trading.api.response;

import trading.domain.Transaction.Transaction;
import trading.domain.Transaction.TransactionBuy;
import trading.domain.Transaction.TransactionSell;
import trading.domain.Transaction.UnsupportedTransactionTypeException;

public class TransactionResponseFactory {
    public static TransactionResponse createTransactionResponse(Transaction transaction) {
        if (transaction instanceof TransactionBuy) {
            return new TransactionBuyResponse(transaction);
        } else if (transaction instanceof TransactionSell) {
            return new TransactionSellResponse(transaction);
        }

        throw new UnsupportedTransactionTypeException(transaction.getTransactionType().toString());
    }
}
