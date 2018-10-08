package trading.persistence;

import trading.domain.Account;
import trading.domain.AccountNumber;
import trading.exception.AccountNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class AccountRepositoryInMemory implements AccountRepository {

    private static Long ACCOUNT_NUMBER_COUNTER = 0L;
    private Map<Long, AccountNumber> investorIdByAccountNumber = new HashMap<>();
    private Map<AccountNumber, Account> accountMap = new HashMap<>();

    public void add(Account account) {
        ACCOUNT_NUMBER_COUNTER++;
        this.investorIdByAccountNumber.put(account.getInvestorId(), account.getAccountNumber());
        this.accountMap.put(account.getAccountNumber(), account);
    }

    public Account findByAccountNumber(AccountNumber accountNumber) throws AccountNotFoundException {
        Account account = this.accountMap.get(accountNumber);
        if (account != null) {
            return account;
        }
        throw new AccountNotFoundException(accountNumber);
    }

    public boolean accountAlreadyExists(Long investorId) {
        return this.investorIdByAccountNumber.containsKey(investorId);
    }

    public Long nextCounterValue() {
        return ACCOUNT_NUMBER_COUNTER + 1;
    }
}
