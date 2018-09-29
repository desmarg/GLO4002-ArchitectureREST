package application;

import api.account.AccountCreatorDto;
import domain.Account;
import exception.AccountAlreadyExistsException;
import persistence.AccountRepository;

public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account create(AccountCreatorDto accountCreatorDto) {
        this.checkIfAccountExists(accountCreatorDto.getInvestorId());
        return this.accountRepository.add(accountCreatorDto);
    }

    public Account findByAccountNumber(Long accountNumber) {
        return this.accountRepository.findByAccountNumber(accountNumber);
    }

    public void checkIfAccountExists(Long investorId) {
        if (this.accountRepository.checkIfAccountExists(investorId)) {
            throw new AccountAlreadyExistsException(investorId);
        }
    }
}
