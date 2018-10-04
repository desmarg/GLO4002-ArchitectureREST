package exception;

import domain.transaction.TransactionNumber;

import javax.ws.rs.core.Response.Status;
import java.util.UUID;

public class StockNotFoundException extends MappedException {

    private UUID transactionNumber;

    public StockNotFoundException(String symbol, String market, TransactionNumber transactionNumber) {
        this.error = "STOCK_NOT_FOUND";
        this.description = "stock '" + symbol + ":" + market + "' not found";
        this.status = Status.BAD_REQUEST;
        this.transactionNumber = transactionNumber.getId();
    }
}