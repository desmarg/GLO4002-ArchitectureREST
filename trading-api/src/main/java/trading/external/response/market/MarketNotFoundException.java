package trading.external.response.market;

import trading.exception.MappedException;

import javax.ws.rs.core.Response;

public class MarketNotFoundException extends MappedException {
    public MarketNotFoundException(String market) {
        super("MARKET_CLOSED", "market '" + market + "' is closed", Response.Status.BAD_REQUEST);
    }
}
