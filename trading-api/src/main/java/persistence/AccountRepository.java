package persistence;

import api.account.PostAccountDto;
import domain.Account;
import domain.AccountNumber;

public interface AccountRepository {

    Account add(PostAccountDto postAccountDto);

    Account findByAccountNumber(AccountNumber accountNumber) throws AccountNotFoundException;

    boolean checkIfAccountExists(Long investorId);
}
