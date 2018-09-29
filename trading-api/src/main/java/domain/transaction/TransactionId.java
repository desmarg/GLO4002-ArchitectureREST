package domain.transaction;

import java.util.UUID;

public class TransactionId {
    private UUID id;

    public TransactionId() {
        this.id = UUID.randomUUID();
    }

    public TransactionId(String id) {
        this.id = UUID.fromString(id);
    }

    public UUID getId() {
        return this.id;
    }

    public String getStringUUID() {
        return this.id.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof TransactionId) {
            return id.equals(((TransactionId) other).getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + id.hashCode();
        return hash;
    }
}
