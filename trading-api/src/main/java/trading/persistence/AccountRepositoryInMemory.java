package trading.persistence;

import trading.domain.Account.Account;
import trading.domain.Account.AccountNumber;
import trading.domain.Account.AccountNotFoundException;
import trading.domain.Account.AccountRepository;

import java.util.HashMap;
import java.util.Map;

public class AccountRepositoryInMemory implements AccountRepository {
    private static Long ACCOUNT_NUMBER_COUNTER = 0L;
    private Map<Long, AccountNumber> investorIdByAccountNumber = new HashMap<>();
    private Map<AccountNumber, Account> accountMap = new HashMap<>();

    public Account save(Account account) {
        AccountNumber accountNumber = new AccountNumber(
                account.getInvestorName(), ACCOUNT_NUMBER_COUNTER++
        );
        account.setAccountNumber(accountNumber);
        this.investorIdByAccountNumber.put(account.getInvestorId(), account.getAccountNumber());
        this.accountMap.put(account.getAccountNumber(), account);
        return account;
    }

    public Account findByAccountNumber(AccountNumber accountNumber)
            throws AccountNotFoundException {
        Account account = this.accountMap.get(accountNumber);
        if (account != null) {
            return account;
        }
        throw new AccountNotFoundException(accountNumber);
    }

    public boolean accountAlreadyExists(Long investorId) {
        return this.investorIdByAccountNumber.containsKey(investorId);
    }

    public Long getCounter() {
        return this.ACCOUNT_NUMBER_COUNTER;
    }
}
