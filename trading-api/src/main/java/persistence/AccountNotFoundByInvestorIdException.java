package persistence;

public class AccountNotFoundByInvestorIdException extends RuntimeException {
    public AccountNotFoundByInvestorIdException(String message) {
        super(message);
    }
}
