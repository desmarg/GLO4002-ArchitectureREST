package trading.services;

import trading.api.request.AccountPostRequestDTO;
import trading.domain.Account.*;

public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account save(AccountPostRequestDTO accountPostRequestDTO) {
        Account account = AccountAssembler.create(accountPostRequestDTO);
        this.checkIfAccountAlreadyExists(account.getInvestorId());
        return this.accountRepository.save(account);
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
