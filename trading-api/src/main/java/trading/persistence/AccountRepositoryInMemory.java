package trading.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import trading.domain.Account.Account;
import trading.domain.Account.AccountNotFoundException;
import trading.domain.Account.AccountNumber;
import trading.domain.Account.AccountRepository;

import java.util.HashMap;
import java.util.Map;

public class AccountRepositoryInMemory implements AccountRepository {
    private static Long ACCOUNT_NUMBER_COUNTER = 0L;
    private Map<Long, AccountNumber> investorIdByAccountNumber = new HashMap<>();
    private Map<AccountNumber, Account> accountMap = new HashMap<>();
    private final Session session;

    public AccountRepositoryInMemory() {
        this.session = buildSessionFactory().openSession();
    }

    private static SessionFactory buildSessionFactory() {
        return new Configuration().configure("./hibernate.cfg.xml").addAnnotatedClass(Account.class).buildSessionFactory();
    }

    public Long save(Account account) {
        Long id = (Long) this.session.save(account);
        return id;
    }

    public Account findByAccountId(Long accountId)
            throws AccountNotFoundException {

        Account account = this.session.get(Account.class, accountId);
        if (account != null) {
            return account;
        }
        throw new AccountNotFoundException(accountId);
    }

    public boolean accountAlreadyExists(Long investorId) {
        return this.investorIdByAccountNumber.containsKey(investorId);
    }

    public Long getCounter() {
        return this.ACCOUNT_NUMBER_COUNTER;
    }
}
