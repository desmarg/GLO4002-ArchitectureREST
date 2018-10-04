package domain;

import java.util.UUID;

public class TransactionNumber {
    private UUID id;

    public TransactionNumber() {
        this.id = UUID.randomUUID();
    }

    public TransactionNumber(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return this.id;
    }

    public String getStringUUID() {
        return this.id.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof TransactionNumber) {
            return this.id.equals(((TransactionNumber) other).getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + this.id.hashCode();
        return hash;
    }
}
