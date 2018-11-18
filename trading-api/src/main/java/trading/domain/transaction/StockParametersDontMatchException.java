package trading.domain.transaction;

import trading.exception.MappedException;

import javax.ws.rs.core.Response.Status;

public class StockParametersDontMatchException extends MappedException {

    public StockParametersDontMatchException() {
        super("STOCK_PARAMETERS_DONT_MATCH", "stock parameters don't match existing ones",
                Status.BAD_REQUEST);
    }
}
