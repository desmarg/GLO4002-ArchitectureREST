package domain;

import java.util.UUID;

public class TransactionID{
    private UUID id;

    public TransactionID(){
        this.id = UUID.randomUUID();
    }

    public TransactionID(UUID id){
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof TransactionID){
            return id.equals(((TransactionID) other).getId());
        }
        return false;
    }
}
