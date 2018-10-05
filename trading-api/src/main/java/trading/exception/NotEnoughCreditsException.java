package trading.exception;

import trading.domain.TransactionNumber;

import javax.ws.rs.core.Response.Status;
import java.util.UUID;

public class NotEnoughCreditsException extends MappedException {

    private UUID transactionNumber;

    public NotEnoughCreditsException(TransactionNumber transactionNumber) {
        this.error = "NOT_ENOUGH_CREDITS";
        this.description = "not enough credits in wallet";
        this.status = Status.BAD_REQUEST;
        this.transactionNumber = transactionNumber.getId();
    }
}