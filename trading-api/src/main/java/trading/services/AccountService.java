package trading.services;

import trading.api.request.AccountPostRequestDTO;
import trading.domain.Account.*;

public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountNumber save(AccountPostRequestDTO accountPostRequestDTO) {
        AccountNumber accountNumber = new AccountNumber(accountPostRequestDTO.investorName, this.accountRepository.getCurrentAccountNumber()+1);
        Account account = AccountAssembler.create(accountPostRequestDTO, accountNumber);
        this.checkIfAccountAlreadyExists(account.getInvestorId());
        this.accountRepository.save(account);
        return account.getAccountNumber();
    }

    public void update(Account account) {
        this.accountRepository.update(account);
    }

    public Account findByAccountNumber(AccountNumber accountNumber) {
        return this.accountRepository.findByAccountNumber(accountNumber);
    }

    private void checkIfAccountAlreadyExists(Long investorId) {
        if (this.accountRepository.accountAlreadyExists(investorId)) {
            throw new AccountAlreadyExistsException(investorId);
        }
    }
}
