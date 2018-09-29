package persistence;

import domain.AccountNumber;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(AccountNumber accountNumber) {
        super("account with number " + accountNumber.toString() + " not found");
    }
}
