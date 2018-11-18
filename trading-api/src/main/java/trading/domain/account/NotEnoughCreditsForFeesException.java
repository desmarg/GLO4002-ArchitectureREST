package trading.domain.account;

import trading.exception.MappedException;

import javax.ws.rs.core.Response.Status;

public class NotEnoughCreditsForFeesException extends MappedException {

    public NotEnoughCreditsForFeesException() {
        super("NOT_ENOUGH_CREDITS", "not enough credits to pay the transaction fee",
                Status.BAD_REQUEST);
    }
}
