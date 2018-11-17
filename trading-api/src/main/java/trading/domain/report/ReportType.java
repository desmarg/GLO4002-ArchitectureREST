package trading.domain.report;

public enum ReportType {
    DAILY;

    public static ReportType fromString(String typeToTest) {
        if (typeToTest == null || typeToTest.equals("")) {
            throw new MissingReportTypeException();
        }
        for (ReportType type : values()) {
            if (type.name().equals(typeToTest)) {
                return type;
            }
        }
        throw new ReportTypeUnsupportedException(typeToTest);
    }
}