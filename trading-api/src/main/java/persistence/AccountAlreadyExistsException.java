package persistence;

import api.account.APIException;

import java.text.MessageFormat;

public class AccountAlreadyExistsException extends APIException {

    public AccountAlreadyExistsException(String message, long investorId) {
        super(message);
        this.errorName = "ACCOUNT_ALREADY_OPEN";
        this.errorDescription = MessageFormat.format("account already open for investor {0}", investorId);
    }
}
