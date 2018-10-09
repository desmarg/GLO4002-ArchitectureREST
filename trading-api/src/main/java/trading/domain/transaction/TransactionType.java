package trading.domain.transaction;

import trading.exception.UnsupportedTransactionTypeException;

public enum TransactionType {
    BUY,
    SELL;

    public static TransactionType fromString(String string) {
        try {
            return TransactionType.valueOf(string);
        } catch (IllegalArgumentException ex) {
            throw new UnsupportedTransactionTypeException(string);
        }
    }
}
