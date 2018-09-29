package domain;

import java.util.UUID;

public class TransactionId {
    private UUID id;

    public TransactionId() {
        this.id = UUID.randomUUID();
    }

    public TransactionId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof TransactionId) {
            return id.equals(((TransactionId) other).getId());
        }
        return false;
    }
}
