package trading.exception;

import trading.domain.transaction.TransactionNumber;

import javax.ws.rs.core.Response.Status;
import java.util.UUID;

public class InvalidTransactionNumberException extends MappedException {

    private UUID transactionNumber;

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
