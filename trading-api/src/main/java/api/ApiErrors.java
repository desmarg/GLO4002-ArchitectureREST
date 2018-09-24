package api;

public class ApiErrors {
    public static final String ACCOUNT_NOT_FOUND = "ACCOUNT_NOT_FOUND";
    public static final String ACCOUNT_ALREADY_OPEN = "ACCOUNT_ALREADY_OPEN";
    public static final String INVALID_AMOUNT = "INVALID_AMOUNT";
    public static final String REQUEST_ERROR = "REQUEST_ERROR";

    private ApiErrors() {
        throw new AssertionError();
    }
}
