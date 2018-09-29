package exception;

import api.account.APIException;

public class AccountNotFoundByAccountNumberException extends APIException {

    public AccountNotFoundByAccountNumberException(String message, long accountNumber) {
        super(message);
        this.errorName = "ACCOUNT_NOT_FOUND";
        this.errorDescription = "account with number " + accountNumber + " not found";
    }
}
