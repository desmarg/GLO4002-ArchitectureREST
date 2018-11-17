package trading.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import trading.domain.account.Account;
import trading.domain.account.AccountNumber;
import trading.domain.account.AccountRepository;

import java.util.List;

public class AccountRepositoryInMemory implements AccountRepository {
    private final SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(AccountHibernateDTO.class).buildSessionFactory();

    @Override
    public void save(Account account) {
        this.validateAccountDoesNotExists(account.getInvestorId());
        this.update(account);
    }

    @Override
    public void update(Account account) {
        Session session = this.sessionFactory.getCurrentSession();
        AccountHibernateDTO accountHibernateDTO = AccountHydrator.toHibernateDto(account);
        session.beginTransaction();
        session.saveOrUpdate(accountHibernateDTO);
        session.getTransaction().commit();
    }

    @Override
    public Account findByAccountNumber(AccountNumber accountNumber)
            throws AccountNotFoundException {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Integer accountId = accountNumber.getId();
        AccountHibernateDTO accountHibernateDTO = session.get(AccountHibernateDTO.class, accountId);

        session.getTransaction().commit();
        if (accountHibernateDTO == null) {
            throw new AccountNotFoundException(accountNumber);
        }
        return AccountHydrator.toAccount(accountHibernateDTO);
    }

    private void validateAccountDoesNotExists(Long investorId) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Object> sameInvestorIdAccounts = session
                .createSQLQuery("select * from ACCOUNTS where investorId = :investorId")
                .setParameter("investorId", investorId).list();
        session.getTransaction().commit();

        if (!sameInvestorIdAccounts.isEmpty()) {
            throw new AccountAlreadyExistsException(investorId);
        }
    }

    @Override
    public Integer getCurrentAccountId() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Object> latestAccountNumber =
                session.createSQLQuery("SELECT MAX(id) from ACCOUNTS").list();
        session.getTransaction().commit();
        if (latestAccountNumber.get(0) == null) {
            latestAccountNumber.set(0, 0);
        }
        return (Integer) latestAccountNumber.get(0);
    }

}