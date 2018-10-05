package trading.exception;

import trading.domain.transaction.TransactionNumber;

import javax.ws.rs.core.Response.Status;
import java.util.UUID;

public class StockParametersDontMatchException extends MappedException {

    private UUID transactionNumber;

    public StockParametersDontMatchException(TransactionNumber transactionNumber) {
        super(
                "STOCK_NOT_FOUND",
                "stock parameters don't match existing ones",
                Status.BAD_REQUEST
        );
        this.transactionNumber = transactionNumber.getId();
    }
}