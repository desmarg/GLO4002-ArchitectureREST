package trading.domain.transaction;

public enum TransactionType {
    BUY, SELL;


    public static TransactionType fromString(String typeToTest) {
        for (TransactionType type : values()) {
            if (type.name().equals(typeToTest)) {
                return type;
            }
        }
        throw new UnsupportedTransactionTypeException(typeToTest);
    }
}
