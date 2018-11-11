package trading.domain.Report;

import trading.exception.MappedException;

import javax.ws.rs.core.Response;

public class MissingReportTypeException extends MappedException {
    public MissingReportTypeException() {
        super(
                "MISSING_REPORT_TYPE",
                "report type is missing",
                Response.Status.BAD_REQUEST
        );
    }
}
