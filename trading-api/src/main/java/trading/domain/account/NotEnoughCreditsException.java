package trading.domain.account;

import trading.domain.transaction.TransactionNumber;
import trading.exception.MappedException;

import javax.ws.rs.core.Response.Status;
import java.util.UUID;

public class NotEnoughCreditsException extends MappedException {
    public NotEnoughCreditsException() {
        super("NOT_ENOUGH_CREDITS", "not enough credits in wallet", Status.BAD_REQUEST);
    }
}
