package api.account;

public class ErrorResponse {

    private String error;
    private String description;

    public ErrorResponse(){}

    public ErrorResponse(Exception e) {
        this.error = "REQUEST_ERROR";
        this.description = "there was an error with the request";
    }

    public ErrorResponse(APIException e) {
        this.error = e.getErrorName();
        this.description = e.getErrorDescription();
    }

    public ErrorResponse(APIRunTimeException e) {
        this.error = e.getErrorName();
        this.description = e.getErrorDescription();
    }

    public String getName() {
        return this.error;
    }

    public String getDescription() {
        return this.description;
    }
}
