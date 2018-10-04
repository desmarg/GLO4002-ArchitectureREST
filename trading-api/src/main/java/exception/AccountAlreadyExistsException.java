package exception;

public class AccountAlreadyExistsException extends RuntimeException {

    public AccountAlreadyExistsException(Long investorId) {
        super("account already open for investor " + investorId);
    }
}
