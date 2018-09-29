package persistence;

import api.account.AccountCreatorDto;
import domain.Account;
import exception.AccountNotFoundException;

public interface AccountRepository {

    Account add(AccountCreatorDto accountCreatorDto);

    Account findByAccountNumber(Long accountNumber) throws AccountNotFoundException;

    boolean checkIfAccountExists(Long investorId);
}
