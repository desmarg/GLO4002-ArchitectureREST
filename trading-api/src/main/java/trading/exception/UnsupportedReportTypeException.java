package trading.exception;

import javax.ws.rs.core.Response.Status;

public class UnsupportedReportTypeException extends MappedException {

    public UnsupportedReportTypeException(String reportType) {
        super(
                "REPORT_TYPE_UNSUPPORTED",
                "report " + reportType + " is not supported",
                Status.BAD_REQUEST
        );
    }
}