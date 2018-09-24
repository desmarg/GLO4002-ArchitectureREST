package api.account;

public class InvalidCreditsAmountException extends APIRunTimeException {

    public InvalidCreditsAmountException(String message) {
        super(message);
        this.errorName = "INVALID_AMOUNT";
        this.errorDescription = "credit amount cannot be lower than or equal to zero";
    }
}