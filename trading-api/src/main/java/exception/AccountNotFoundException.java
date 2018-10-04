package exception;

import domain.account.AccountNumber;

import javax.ws.rs.core.Response.Status;

public class AccountNotFoundException extends MappedException {

    public AccountNotFoundException(AccountNumber accountNumber) {
        this.error = "ACCOUNT_NOT_FOUND";
        this.description = "account with number " + accountNumber.getId() + " not found";
        this.status = Status.BAD_REQUEST;
    }
}