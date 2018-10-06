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
        this.checkIfAccountAlreadyExists(account.getInvestorId());
        this.accountRepository.add(account);
    }

    public Account findByAccountNumber(AccountNumber accountNumber) {
        return this.accountRepository.findByAccountNumber(accountNumber);
    }

    public void checkIfAccountAlreadyExists(Long investorId) {
        if (this.accountRepository.accountAlreadyExists(investorId)) {
            throw new AccountAlreadyExistsException(investorId);
        }
    }

    public long nextAccountNumber() {
        return this.accountRepository.nextCounterValue();
    }
}
