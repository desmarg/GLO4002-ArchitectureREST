package application;

import domain.Account;
import persistence.AccountAlreadyExistsException;
import persistence.AccountNotFoundByAccountNumberException;
import persistence.AccountRepository;

public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public long create(Account account) {
        return accountRepository.add(account);
    }

    public Account findByAccountNumber(long accountNumber) throws AccountNotFoundByAccountNumberException {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public void checkIfAccountExists(long investorId) throws AccountAlreadyExistsException{
        if(accountRepository.checkIfAccountExists(investorId)) {
            throw new AccountAlreadyExistsException("Account already exists", investorId);
        }
    }
}
