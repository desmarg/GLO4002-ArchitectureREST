package trading.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import trading.domain.Account.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class AccountRepositoryInMemory implements AccountRepository {
    private final Session session;
    private final AtomicLong ACCOUNT_NUMBER_COUNTER = new AtomicLong();
    private final Map<Long, AccountNumber> investorIdByAccountNumber = new HashMap<>();
    private final Map<AccountNumber, Account> accountMap = new HashMap<>();

    public AccountRepositoryInMemory(){
        this.session = buildSessionFactory().openSession();
    }

    private static SessionFactory buildSessionFactory() {
        return new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Account.class).buildSessionFactory();
    }

    public void save(Account account) {
        this.investorIdByAccountNumber.put(account.getInvestorId(), account.getAccountNumber());
        this.accountMap.put(account.getAccountNumber(), account);
    }

    public void update(Account account) {
        this.investorIdByAccountNumber.put(account.getInvestorId(), account.getAccountNumber());
        this.accountMap.put(account.getAccountNumber(), account);
    }

    public Account findByAccountNumber(AccountNumber accountNumber)
            throws AccountNotFoundException {
        Account account = this.accountMap.get(accountNumber);
        if (account != null) {
            return account;
        }
        throw new AccountNotFoundException(accountNumber);
    }

    public void validateAccountDoesNotExists(Long investorId) {
        if (this.investorIdByAccountNumber.containsKey(investorId)) {
            throw new AccountAlreadyExistsException(investorId);
        }
    }

    public Long getCurrentAccountNumber() {
        return this.ACCOUNT_NUMBER_COUNTER.get();
    }
}
