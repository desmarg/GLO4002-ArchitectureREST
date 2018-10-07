package trading.domain;

import trading.exception.UnsupportedReportTypeException;

public enum ReportType {
    DAILY;

    static public ReportType fromString(String string) {
        try {
            return ReportType.valueOf(string);
        } catch (IllegalArgumentException ex) {
            throw new UnsupportedReportTypeException(string);
        }
    }
}
