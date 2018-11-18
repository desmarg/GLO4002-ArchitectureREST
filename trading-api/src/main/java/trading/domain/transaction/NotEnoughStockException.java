package trading.domain.transaction;

import trading.domain.Stock;
import trading.exception.MappedException;

import javax.ws.rs.core.Response.Status;

public class NotEnoughStockException extends MappedException {

    public NotEnoughStockException(Stock stock) {
        super("NOT_ENOUGH_STOCK",
                "not enough stock '" + stock.getSymbol() + ":" + stock.getMarket() + "'",
                Status.BAD_REQUEST);
    }
}
