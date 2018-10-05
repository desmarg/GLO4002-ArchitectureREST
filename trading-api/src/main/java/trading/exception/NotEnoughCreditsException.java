package trading.exception;

import trading.domain.transaction.TransactionNumber;

import javax.ws.rs.core.Response.Status;
import java.util.UUID;

public class NotEnoughCreditsException extends MappedException {

    private UUID transactionNumber;

    public NotEnoughCreditsException(TransactionNumber transactionNumber) {
        super(
                "NOT_ENOUGH_CREDITS",
                "not enough credits in wallet",
                Status.BAD_REQUEST
        );
        this.transactionNumber = transactionNumber.getId();
    }
}