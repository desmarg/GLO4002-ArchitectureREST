package application;

import api.account.PostAccountDto;
import domain.Account;
import domain.AccountNumber;
import exception.AccountAlreadyExistsException;
import persistence.AccountRepository;

public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account create(PostAccountDto postAccountDto) {
        this.checkIfAccountExists(postAccountDto.getInvestorId());
        return this.accountRepository.add(postAccountDto);
    }

    public Account findByAccountNumber(AccountNumber accountNumber) {
        return this.accountRepository.findByAccountNumber(accountNumber);
    }

    public void checkIfAccountExists(Long investorId) {
        if (this.accountRepository.checkIfAccountExists(investorId)) {
            throw new AccountAlreadyExistsException(investorId);
        }
    }
}
