package trading.domain.transaction;

import trading.domain.transaction.TransactionNumber;
import trading.exception.MappedException;

import javax.ws.rs.core.Response.Status;

public class TransactionNotFoundException extends MappedException {
    public TransactionNotFoundException(TransactionNumber transactionNumber) {
        super(
                "TRANSACTION_NOT_FOUND",
                "transaction with number " + transactionNumber.getId() + " not found",
                Status.NOT_FOUND
        );
    }
}
