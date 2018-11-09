package trading.exception;

import trading.domain.transaction.TransactionNumber;

import javax.ws.rs.core.Response.Status;
import java.util.UUID;

public class StockParametersDontMatchException extends MappedException {
    private UUID transactionNumber;

    public StockParametersDontMatchException() {
        super(
                "STOCK_PARAMETERS_DONT_MATCH",
                "stock parameters don't match existing ones",
                Status.BAD_REQUEST
        );
        this.transactionNumber = new TransactionNumber().getId();
    }

    public UUID getTransactionNumber() {
        return this.transactionNumber;
    }
}
