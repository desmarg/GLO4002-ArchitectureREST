package trading.exception;

import trading.domain.Stock;
import trading.domain.TransactionNumber;

import javax.ws.rs.core.Response.Status;
import java.util.UUID;

public class NotEnoughStockException extends MappedException {

    private UUID transactionNumber;

    public NotEnoughStockException(Stock stock, TransactionNumber transactionNumber) {
        this.error = "INVALID_TRANSACTION_NUMBER";
        this.description = "not enough stock '" + stock.getSymbol() + ":" + stock.getMarket();
        this.status = Status.BAD_REQUEST;
        this.transactionNumber = transactionNumber.getId();
    }
}