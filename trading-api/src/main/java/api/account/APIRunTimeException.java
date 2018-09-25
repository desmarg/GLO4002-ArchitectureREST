package api.account;

public abstract class APIRunTimeException extends RuntimeException {

    protected String errorName;
    protected String errorDescription;

    public APIRunTimeException(){}

    public APIRunTimeException(String message){
        super(message);
    }

    public String getErrorName(){
        return this.errorName;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
