package persistence;

import api.account.AccountCreatorDto;
import domain.Account;
import domain.AccountNumber;
import exception.AccountNotFoundException;

public interface AccountRepository {

    Account add(AccountCreatorDto accountCreatorDto);

    Account findByAccountNumber(AccountNumber accountNumber) throws AccountNotFoundException;

    boolean checkIfAccountExists(Long investorId);
}
