package persistence;

public class AccountNotFoundByAccountNumberException extends RuntimeException {

    public AccountNotFoundByAccountNumberException(String message){
        super(message);
    }
}
