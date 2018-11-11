package trading.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import trading.domain.Account.Account;
import trading.domain.Account.AccountNotFoundException;
import trading.domain.Account.AccountRepository;

public class AccountRepositoryInMemory implements AccountRepository {
    private final Session session;

    public AccountRepositoryInMemory() {
        this.session = buildSessionFactory().openSession();
    }

    private static SessionFactory buildSessionFactory() {
        return new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Account.class).buildSessionFactory();
    }

    public Long save(Account account) {
        return (Long) this.session.save(account);
    }

    public Account findByAccountNumber(Long accountNumber)
            throws AccountNotFoundException {

        Account account = this.session.get(Account.class, accountNumber);
        if (account != null) {
            return account;
        }
        throw new AccountNotFoundException(accountNumber);
    }

    public boolean accountAlreadyExists(Long accountNumber) {
        Account account = this.session.get(Account.class, accountNumber);
        if (account != null) {
            return true;
        } else {
            return false;
        }
    }
}
