package trading.external.response.Market;

import trading.exception.MappedException;

import javax.ws.rs.core.Response;

public class MarketClosedException extends MappedException {
    public MarketClosedException(String market) {
        super(
                "MARKET_CLOSED",
                "market '" + market + "' is closed",
                Response.Status.BAD_REQUEST
        );
    }
}
