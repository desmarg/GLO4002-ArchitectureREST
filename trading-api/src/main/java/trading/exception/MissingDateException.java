package trading.exception;

import trading.domain.transaction.TransactionNumber;

import javax.ws.rs.core.Response.Status;
import java.util.UUID;

public class MissingDateException extends MappedException {

    public MissingDateException() {
        super(
                "MISSING_DATE",
                "date is missing",
                Status.BAD_REQUEST
        );
    }
}