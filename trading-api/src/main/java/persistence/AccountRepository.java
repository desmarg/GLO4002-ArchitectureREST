package persistence;

import api.account.PostAccountDto;
import domain.Account;

public interface AccountRepository {

    Account add(PostAccountDto postAccountDto);

    Account findByAccountNumber(Long accountNumber) throws AccountNotFoundException;

    boolean checkIfAccountExists(Long investorId);
}
