package exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(Long accountNumber) {
        super("account with number " + accountNumber + " not found");
    }
}
