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

    public void saveAccount(Account account) {
        this.checkIfAccountExists(account.getInvestorId());
        this.accountRepository.add(account);
    }

    public Account findByAccountNumber(AccountNumber accountNumber) {
        return this.accountRepository.findByAccountNumber(accountNumber);
    }

    public void checkIfAccountExists(Long investorId) {
        if (this.accountRepository.checkIfAccountExists(investorId)) {
            throw new AccountAlreadyExistsException(investorId);
        }
    }

    public long nextAccountNumber() {
        return this.accountRepository.nextCounterValue();
    }
}