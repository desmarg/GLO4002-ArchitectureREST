package api.account;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ErrorResponse {

    private String error;
    private String description;

    //Allows to not show null attributes
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String transactionId;

    public ErrorResponse(String errorName, String errorDescription) {
        this.error = errorName;
        this.description = errorDescription;
    }

    public ErrorResponse(String errorName, String errorDescription, String transactionId) {
        this.error = errorName;
        this.description = errorDescription;
        this.transactionId = transactionId;
    }


    public String getError() {
        return this.error;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTransactionId() {
        return transactionId;
    }
}
