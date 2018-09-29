package exception;

import api.account.APIException;
import domain.AccountNumber;

public class AccountNotFoundByAccountNumberException extends APIException {

    public AccountNotFoundByAccountNumberException(String message, AccountNumber accountNumber) {
        super(message);
        this.errorName = "ACCOUNT_NOT_FOUND";
        this.errorDescription = "account with number " + accountNumber.toString() + " not found";
    }
}
