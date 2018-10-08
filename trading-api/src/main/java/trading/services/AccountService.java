package trading.services;

import trading.domain.Account;
import trading.domain.AccountNumber;
import trading.exception.AccountAlreadyExistsException;
import trading.persistence.AccountRepository;

public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account save(Account account) {
        this.checkIfAccountAlreadyExists(account.getInvestorId());
        AccountNumber accountNumber = new AccountNumber(
                account.getInvestorName(),
                this.accountRepository.nextCounterValue()
        );
        account.setAccountNumber(accountNumber);
        this.accountRepository.add(account);

        return account;
    }

    public Account findByAccountNumber(AccountNumber accountNumber) {
        return this.accountRepository.findByAccountNumber(accountNumber);
    }

    public void checkIfAccountAlreadyExists(Long investorId) {
        if (this.accountRepository.accountAlreadyExists(investorId)) {
            throw new AccountAlreadyExistsException(investorId);
        }
    }
}
