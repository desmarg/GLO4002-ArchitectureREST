package trading.services;

import trading.api.request.AccountPostRequestDTO;
import trading.domain.Account.*;

public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Long save(AccountPostRequestDTO accountPostRequestDTO) {
        Account account = AccountAssembler.create(accountPostRequestDTO);
        if(account.getId() != null) {
            throw new AccountAlreadyExistsException(account.getId());
        }
        return this.accountRepository.save(account);
    }

    public Account findByAccountNumber(Long accountNumber) {
        return this.accountRepository.findByAccountNumber(accountNumber);
    }

    private void checkIfAccountAlreadyExists(Long investorId) {
        if (this.accountRepository.accountAlreadyExists(investorId)) {
            throw new AccountAlreadyExistsException(investorId);
        }
    }
}
