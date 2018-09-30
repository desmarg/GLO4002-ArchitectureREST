package domain.transaction;

import exception.UnsupportedTransactionTypeException;

public enum TransactionType {
    BUY,
    SELL;

    public static TransactionType getType(String typeToTest) {
        for (TransactionType type : values()) {
            if (type.name().equals(typeToTest)){
                return type;
            }
        }
        throw new UnsupportedTransactionTypeException(typeToTest);
    }
}