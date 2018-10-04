package exception;

public class InvalidCreditsAmountException extends RuntimeException {

    public InvalidCreditsAmountException() {
        super("credit amount cannot be lower than or equal to zero");
    }
}