package trading.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import trading.domain.Account.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class AccountRepositoryInMemory implements AccountRepository {
    private final AtomicLong ACCOUNT_NUMBER_COUNTER = new AtomicLong();
    private final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(AccountHibernateDTO.class).buildSessionFactory();

    public void save(Account account) {
        Session session = this.sessionFactory.getCurrentSession();
        AccountHibernateDTO accountHibernateDTO = AccountHydrator.toHibernateDto(account);
        session.beginTransaction();
        session.saveOrUpdate(accountHibernateDTO);
        session.getTransaction().commit();
        this.incrementCounter();
    }

    public void update(Account account) {
        this.save(account);
    }

    public Account findByAccountNumber(AccountNumber accountNumber)
            throws AccountNotFoundException {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        String accountNumberAsString = accountNumber.getId();
        AccountHibernateDTO accountHibernateDTO = session.get(AccountHibernateDTO.class, accountNumberAsString);

        session.getTransaction().commit();
        if (accountHibernateDTO == null) {
            throw new AccountNotFoundException(accountNumber);
        }
        return AccountHydrator.toAccount(accountHibernateDTO);
    }

    public void validateAccountDoesNotExists(Long investorId) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Object> sameInvestorIdAccounts = session.createSQLQuery("select * from ACCOUNTS where investorId = :investorId").
                setParameter("investorId", investorId).list();
        session.getTransaction().commit();

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