package trading.exception;

import trading.domain.transaction.TransactionNumber;

import javax.ws.rs.core.Response.Status;
import java.util.UUID;

public class NotEnoughCreditsForFeesException extends MappedException {
    private UUID transactionNumber;

    public NotEnoughCreditsForFeesException(TransactionNumber transactionNumber) {
        super(
                "NOT_ENOUGH_CREDITS",
                "not enough credits to pay the transaction fee",
                Status.BAD_REQUEST
        );
        this.transactionNumber = transactionNumber.getId();
    }

    public UUID getTransactionNumber() {
        return this.transactionNumber;
    }
}
