package trading.services;

import trading.api.request.AccountPostRequestDTO;
import trading.domain.Account.Account;
import trading.domain.Account.AccountAssembler;
import trading.domain.Account.AccountNumber;
import trading.domain.Account.AccountRepository;

public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountNumber save(AccountPostRequestDTO accountPostRequestDTO) {
        Integer id = this.accountRepository.getCurrentAccountId() + 1;
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