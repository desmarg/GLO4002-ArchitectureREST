package trading.domain.transaction;

import trading.exception.UnsupportedTransactionTypeException;

public enum TransactionType {
    BUY,
    SELL;

    static public TransactionType lookup(String transactionType) {
        try {
            return TransactionType.valueOf(transactionType);
        } catch (Exception ex) {
            throw new UnsupportedTransactionTypeException(transactionType);
        }
    }
}