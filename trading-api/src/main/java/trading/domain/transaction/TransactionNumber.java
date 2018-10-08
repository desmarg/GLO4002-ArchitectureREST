package trading.domain.transaction;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.UUID;

public class TransactionNumber {
    private UUID id;
    private final int FIRST_ODD_NUMBER = 17;
    private final int SECOND_ODD_NUMBER = 31;

    public TransactionNumber() {
        this.id = UUID.randomUUID();
    }

    public TransactionNumber(UUID id) {
        this.id = id;
    }

    public TransactionNumber(String text) {
        this.id = UUID.fromString(text);
    }

    @JsonValue
    public String toString() {
        return this.id.toString();
    }

    public UUID getId() {
        return this.id;
    }

    public String getStringUUID() {
        return this.id.toString();
    }

    @Override
    public boolean equals(Object otherTransactionNumber) {
        if (otherTransactionNumber instanceof TransactionNumber) {
            return this.id.equals(((TransactionNumber) otherTransactionNumber).getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.FIRST_ODD_NUMBER * this.SECOND_ODD_NUMBER + this.id.hashCode();
    }
}
