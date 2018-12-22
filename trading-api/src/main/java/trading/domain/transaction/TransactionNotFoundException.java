package trading.domain.transaction;

import trading.exception.MappedException;

import javax.ws.rs.core.Response.Status;

public class TransactionNotFoundException extends MappedException {
    public TransactionNotFoundException(TransactionID transactionID) {
        super("TRANSACTION_NOT_FOUND", "transaction with number " + transactionID.getId()
                + " not found", Status.NOT_FOUND);
    }
}
