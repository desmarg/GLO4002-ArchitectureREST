package trading.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import trading.domain.Account.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class AccountRepositoryInMemory implements AccountRepository {
    private final Session session;
    private final AtomicLong ACCOUNT_NUMBER_COUNTER = new AtomicLong();

    public AccountRepositoryInMemory() {
        this.session = buildSessionFactory().openSession();
    }

    private static SessionFactory buildSessionFactory() {
        return new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(AccountHibernateDTO.class).buildSessionFactory();
    }

    public void save(Account account) {
        AccountHibernateDTO accountHibernateDTO = AccountHydrator.toHibernateDto(account);
        this.session.beginTransaction();
        this.session.saveOrUpdate(accountHibernateDTO);
        this.session.getTransaction().commit();
        this.incrementCounter();
    }

    public void update(Account account) {
        this.save(account);
    }

    public Account findByAccountNumber(AccountNumber accountNumber)
            throws AccountNotFoundException {
        String accountNumberAsString = accountNumber.getId();
        AccountHibernateDTO accountHibernateDTO = this.session.get(AccountHibernateDTO.class, accountNumberAsString);
        if (accountHibernateDTO == null) {
            throw new AccountNotFoundException(accountNumber);
        }
        return AccountHydrator.toAccount(accountHibernateDTO);
    }

    public void validateAccountDoesNotExists(Long investorId) {
        List<Object> sameInvestorIdAccounts = this.session.createSQLQuery("select * from ACCOUNTS where investorId = :investorId").setParameter("investorId", investorId).list();

        if (!sameInvestorIdAccounts.isEmpty()) {
            throw new AccountAlreadyExistsException(investorId);
        }
    }

    public Long getCurrentAccountNumber() {
        return this.ACCOUNT_NUMBER_COUNTER.get();
    }

    private void incrementCounter() {
        this.ACCOUNT_NUMBER_COUNTER.set(this.ACCOUNT_NUMBER_COUNTER.incrementAndGet());
    }
}
