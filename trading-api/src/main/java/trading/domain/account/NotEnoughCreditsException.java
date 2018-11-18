package trading.domain.account;

import trading.exception.MappedException;
import javax.ws.rs.core.Response.Status;

public class NotEnoughCreditsException extends MappedException {
    public NotEnoughCreditsException() {
        super("NOT_ENOUGH_CREDITS", "not enough credits in wallet", Status.BAD_REQUEST);
    }
}
