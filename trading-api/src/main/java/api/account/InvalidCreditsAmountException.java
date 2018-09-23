package api.account;

public class InvalidCreditsAmountException extends RuntimeException {

    public InvalidCreditsAmountException(String message){
        super(message);
    }
}