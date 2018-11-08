package trading.domain.transaction;

public enum TransactionType {
    BUY,
    SELL;

    public static TransactionType fromString(String string) {
        return TransactionType.valueOf(string);
    }
}
