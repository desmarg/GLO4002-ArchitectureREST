package exception;

import domain.account.AccountNumber;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(AccountNumber accountNumber) {
        super("account with number " + accountNumber.getId() + " not found");
    }
}
