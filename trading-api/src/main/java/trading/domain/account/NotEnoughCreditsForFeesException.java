package trading.domain.account;

import trading.domain.transaction.TransactionNumber;
import trading.exception.MappedException;

import javax.ws.rs.core.Response.Status;
import java.util.UUID;

public class NotEnoughCreditsForFeesException extends MappedException {
    private final UUID transactionNumber;

    public NotEnoughCreditsForFeesException() {
        super("NOT_ENOUGH_CREDITS", "not enough credits to pay the transaction fee",
                Status.BAD_REQUEST);
        this.transactionNumber = new TransactionNumber().getId();
    }

    public UUID getTransactionNumber() {
        return this.transactionNumber;
    }
}
