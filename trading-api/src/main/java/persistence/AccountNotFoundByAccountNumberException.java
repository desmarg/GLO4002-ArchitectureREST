package persistence;

import api.account.APIException;

import java.text.MessageFormat;

public class AccountNotFoundByAccountNumberException extends APIException {

    public AccountNotFoundByAccountNumberException(String message, long accountNumber) {
        super(message);
        this.errorName = "ACCOUNT_NOT_FOUND";
        this.errorDescription = MessageFormat.format("account with number {0} not found", accountNumber);
    }
}
