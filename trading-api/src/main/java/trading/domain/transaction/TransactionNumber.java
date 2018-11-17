package trading.domain.transaction;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.UUID;

public class TransactionNumber {
    private final UUID id;

    public TransactionNumber() {
        this.id = UUID.randomUUID();
    }

    public TransactionNumber(UUID id) {
        this.id = id;
    }

    public TransactionNumber(String text) {
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
        if (otherTransactionNumber instanceof TransactionNumber) {
            return this.id.equals(((TransactionNumber) otherTransactionNumber).getId());
        }

        return false;
    }

    @Override
    public int hashCode() {
        Integer SECOND_ODD_NUMBER = 31;
        Integer FIRST_ODD_NUMBER = 17;
        return FIRST_ODD_NUMBER * SECOND_ODD_NUMBER + this.id.hashCode();
    }
}
