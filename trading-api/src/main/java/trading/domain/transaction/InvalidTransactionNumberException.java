package trading.domain.transaction;

import trading.exception.MappedException;

import javax.ws.rs.core.Response.Status;
import java.util.UUID;

public class InvalidTransactionNumberException extends MappedException {
    private final UUID transactionNumber;

    public InvalidTransactionNumberException(TransactionNumber transactionNumber) {
        super(
                "INVALID_TRANSACTION_NUMBER",
                "the transaction number is invalid",
                Status.BAD_REQUEST
        );
        this.transactionNumber = transactionNumber.getId();
    }

    public UUID getTransactionNumber() {
        return this.transactionNumber;
    }
}
