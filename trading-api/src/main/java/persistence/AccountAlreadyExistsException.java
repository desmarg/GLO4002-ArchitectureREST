package persistence;

import api.account.APIException;

import java.text.MessageFormat;

public class AccountAlreadyExistsException extends APIException {

    public AccountAlreadyExistsException(String message, long investorId) {
        super(message);
        this.errorName = "ACCOUNT_ALREADY_OPEN";
        this.errorDescription = "account already open for investor " + investorId;
    }
}
