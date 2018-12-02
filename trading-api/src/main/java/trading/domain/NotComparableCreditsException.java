package trading.domain;

import trading.exception.MappedException;

import javax.ws.rs.core.Response;

public class NotComparableCreditsException extends MappedException {
    public NotComparableCreditsException() {
        super("CURRENCIES_NOT_COMPARABLE", "unable to compare credits in different currencies", Response.Status.EXPECTATION_FAILED);
    }
}
