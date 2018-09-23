package application;

import domain.Account;
import persistence.AccountRepository;

public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public long create(Account account){
        return accountRepository.add(account);
    }

    public Account findByAccountNumber(long accountNumber){
        return accountRepository.findByAccountNumber(accountNumber);
    }
}
