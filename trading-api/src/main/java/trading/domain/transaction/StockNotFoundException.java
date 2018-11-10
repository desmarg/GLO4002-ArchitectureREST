package trading.domain.transaction;

import trading.exception.MappedException;

import javax.ws.rs.core.Response.Status;

public class StockNotFoundException extends MappedException {

    public StockNotFoundException(String symbol, String market) {
        super(
                "STOCK_NOT_FOUND",
                "stock '" + symbol + ":" + market + "' not found",
                Status.BAD_REQUEST
        );
    }

}
