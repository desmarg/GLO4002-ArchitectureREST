package trading.domain.transaction;

import trading.domain.Stock;
import trading.exception.MappedException;

import javax.ws.rs.core.Response.Status;
import java.util.UUID;

public class NotEnoughStockException extends MappedException {
    private final UUID transactionNumber;

    public NotEnoughStockException(Stock stock) {
        super("NOT_ENOUGH_STOCK",
                "not enough stock '" + stock.getSymbol() + ":" + stock.getMarket() + "'",
                Status.BAD_REQUEST);
        this.transactionNumber = new TransactionNumber().getId();
    }

    public UUID getTransactionNumber() {
        return this.transactionNumber;
    }
}
