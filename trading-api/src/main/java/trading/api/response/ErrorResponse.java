package trading.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import trading.domain.TransactionNumber;

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

    public ErrorResponse(String errorName, String errorDescription, TransactionNumber transactionNumber) {
        this.error = errorName;
        this.description = errorDescription;
        this.transactionId = transactionNumber.getId().toString();
    }


    public String getError() {
        return this.error;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTransactionId() {
        return this.transactionId;
    }
}
