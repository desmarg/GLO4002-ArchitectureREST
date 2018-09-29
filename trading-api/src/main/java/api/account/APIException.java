package api.account;

public abstract class APIException extends Exception {

    protected String errorName;
    protected String errorDescription;

    public APIException() {
    }

    ;

    public APIException(String message) {
        super(message);
    }

    public String getErrorName() {
        return this.errorName;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
