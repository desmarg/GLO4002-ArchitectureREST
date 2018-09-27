package api.account;

public class ErrorResponse {

    private String error;
    private String description;

    public ErrorResponse(String errorName, String errorDescription) {
        this.error = errorName;
        this.description = errorDescription;
    }

    public String getError() {
        return this.error;
    }

    public String getDescription() {
        return this.description;
    }
}
