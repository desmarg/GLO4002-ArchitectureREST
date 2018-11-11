package trading.domain.Account;

import trading.domain.Transaction.TransactionNumber;
import trading.exception.MappedException;

import javax.ws.rs.core.Response.Status;
import java.util.UUID;

public class NotEnoughCreditsException extends MappedException {
    private final UUID transactionNumber;

    public NotEnoughCreditsException(TransactionNumber transactionNumber) {
        super(
                "NOT_ENOUGH_CREDITS",
                "not enough credits in wallet",
                Status.BAD_REQUEST
        );
        this.transactionNumber = transactionNumber.getId();
    }

    public UUID getTransactionNumber() {
        return this.transactionNumber;
    }
}
