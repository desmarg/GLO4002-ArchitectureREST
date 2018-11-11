package trading.services;

import trading.api.request.AccountPostRequestDTO;
import trading.domain.Account.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class AccountService {
    private final AccountRepository accountRepository;
    private final WalletService walletService;

    public AccountService(AccountRepository accountRepository, WalletService walletService) {
        this.accountRepository = accountRepository;
        this.walletService = walletService;
    }

    public Account save(AccountPostRequestDTO accountPostRequestDTO) {
        Account account = AccountAssembler.create(accountPostRequestDTO);
        this.checkIfAccountAlreadyExists(account.getInvestorId());
        Account savedAccount = this.accountRepository.save(account);
        this.walletService.save(savedAccount, Instant.now().truncatedTo(ChronoUnit.DAYS));
        return savedAccount;
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
