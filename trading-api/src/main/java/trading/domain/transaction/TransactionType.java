package trading.domain.transaction;

import trading.exception.UnsupportedTransactionTypeException;

public enum TransactionType {
    BUY,
    SELL;

    static public TransactionType lookup(String type) {
        try {
            return TransactionType.valueOf(type);
        } catch (IllegalArgumentException ex) {
            throw new UnsupportedTransactionTypeException(type);
        }
    }
}