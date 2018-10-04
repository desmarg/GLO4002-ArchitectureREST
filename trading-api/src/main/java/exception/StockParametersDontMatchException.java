package exception;

import domain.transaction.TransactionNumber;

import javax.ws.rs.core.Response.Status;
import java.util.UUID;

public class StockParametersDontMatchException extends MappedException {

    private UUID transactionNumber;

    public StockParametersDontMatchException(TransactionNumber transactionNumber) {
        this.error = "STOCK_NOT_FOUND";
        this.description = "stock parameters don't match existing ones";
        this.status = Status.BAD_REQUEST;
        this.transactionNumber = transactionNumber.getId();
    }
}