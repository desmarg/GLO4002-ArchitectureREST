package trading.api.response;

import trading.domain.transaction.Transaction;
import trading.domain.transaction.TransactionBuy;
import trading.domain.transaction.TransactionSell;
import trading.domain.transaction.UnsupportedTransactionTypeException;

public class TransactionResponseDTOFactory {
    public static TransactionResponseDTO createTransactionResponse(Transaction transaction) {
        if (transaction instanceof TransactionBuy) {
            return new TransactionBuyResponseDTO(transaction);
        } else if (transaction instanceof TransactionSell) {
            return new TransactionSellResponseDTO(transaction);
        }

        throw new UnsupportedTransactionTypeException(transaction.getTransactionType().toString());
    }
}
