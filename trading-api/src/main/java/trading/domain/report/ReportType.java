package trading.domain.report;

public enum ReportType {
    DAILY;

    public static void validateReportType(String typeToTest) {
        if (typeToTest == null || typeToTest.equals("")) {
            throw new MissingReportTypeException();
        }
        for (ReportType type : values()) {
            if (type.name().equals(typeToTest)) {
                return;
            }
        }
        throw new ReportTypeUnsupportedException(typeToTest);
    }
}