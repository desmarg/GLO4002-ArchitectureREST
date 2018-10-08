package trading.exception;

import trading.domain.transaction.TransactionNumber;

import javax.ws.rs.core.Response.Status;
import java.util.UUID;

public class StockNotFoundException extends MappedException {
    private UUID transactionNumber;

    public StockNotFoundException(String symbol, String market, TransactionNumber
            transactionNumber) {
        super(
                "STOCK_NOT_FOUND",
                "stock '" + symbol + ":" + market + "' not found",
                Status.BAD_REQUEST
        );
        this.transactionNumber = transactionNumber.getId();
    }

    public UUID getTransactionNumber() {
        return this.transactionNumber;
    }
}
