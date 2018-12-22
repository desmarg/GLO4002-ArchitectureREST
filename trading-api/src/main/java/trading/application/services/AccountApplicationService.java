package trading.application.services;

import trading.api.request.AccountPostRequestDTO;
import trading.domain.account.Account;
import trading.domain.account.AccountAssembler;
import trading.domain.account.AccountNumber;
import trading.domain.account.AccountRepository;

public class AccountApplicationService {
    private final AccountRepository accountRepository;

    public AccountApplicationService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountNumber createAccount(AccountPostRequestDTO accountPostRequestDTO) {
        Integer id = this.accountRepository.getNumberOfAccounts() + 1;
        Account account = AccountAssembler.create(accountPostRequestDTO, id);
        this.accountRepository.save(account);

        return account.getAccountNumber();
    }

    public void update(Account account) {
        this.accountRepository.update(account);
    }

    public Account findByAccountNumber(String accountNumberString) {
        AccountNumber accountNumber = new AccountNumber(accountNumberString);
        return this.accountRepository.findByAccountNumber(accountNumber);
    }
}