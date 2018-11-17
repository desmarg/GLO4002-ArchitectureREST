package trading.domain.report;

import trading.exception.MappedException;

import javax.ws.rs.core.Response;

public class ReportTypeUnsupportedException extends MappedException {

    public ReportTypeUnsupportedException(String transactionType) {
        super("REPORT_TYPE_UNSUPPORTED", "report " + transactionType + " is not supported",
                Response.Status.BAD_REQUEST);
    }
}
