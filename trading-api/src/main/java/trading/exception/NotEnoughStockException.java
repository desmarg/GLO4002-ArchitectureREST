package trading.exception;

import trading.domain.Stock;
import trading.domain.transaction.TransactionNumber;

import javax.ws.rs.core.Response.Status;
import java.util.UUID;

public class NotEnoughStockException extends MappedException {

    private UUID transactionNumber;

    public NotEnoughStockException(Stock stock, TransactionNumber transactionNumber) {
        super(
                "NOT_ENOUGH_STOCK",
                "not enough stock '" + stock.getSymbol() + ":" + stock.getMarket() + "'",
                Status.BAD_REQUEST
        );
        this.transactionNumber = transactionNumber.getId();
    }

    public UUID getTransactionNumber() {
        return this.transactionNumber;
    }
}
