package persistence;

public class AccountAlreadyExistsException extends RuntimeException {

    public AccountAlreadyExistsException(String message){
        super(message);
    }
}
