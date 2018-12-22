package trading.domain.transaction;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.UUID;

public class TransactionID {
    private final UUID id;

    public TransactionID() {
        this.id = UUID.randomUUID();
    }

    public TransactionID(UUID id) {
        this.id = id;
    }

    public TransactionID(String text) {
        this.id = UUID.fromString(text);
    }

    @Override
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
        if (otherTransactionNumber instanceof TransactionID) {
            return this.id.equals(((TransactionID) otherTransactionNumber).getId());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return 17 * 31 + this.id.hashCode();
    }
}
