package trading.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import trading.domain.Account.Account;
import trading.domain.Account.AccountNotFoundException;
import trading.domain.Account.AccountNumber;
import trading.domain.Account.AccountRepository;

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
        this.session.saveOrUpdate(accountHibernateDTO);
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
//        Query query = this.session.createQuery("from AccountHibernateDTO where investorId = :investorId");
//        query.setParameter("investorId", investorId);
//        List list = ((org.hibernate.query.Query) query).list();
//        AccountHibernateDTO accountHibernateDTO = this.session.get(AccountHibernateDTO.class, investorId);
//        Query query = this.session.createSQLQuery("SELECT count(*) from ACCOUNTS where investorId = :investorId");
//        query.setParameter("investorId", investorId);
//        List list = query.list();
//        if (!(list.get(0)).equals(BigInteger.ZERO)) {
//            throw new AccountAlreadyExistsException(investorId);
//        }
//        Query query = this.session.
//                createQuery("from AccountHibernateDTO t where t.investorId = :investorId");
//        query.setParameter("investorId", investorId);
//        Object object = query.uniqueResult();
//        String hello = "hello";
//        List list = this.session.createCriteria(AccountHibernateDTO.class).list();
//        String ok = "ok";

        List<Object> salut = this.session.createQuery("select table_name from all_tables").list();

        for (Object allo : salut) {
            System.out.println(allo);
        }
    }

    public Long getCurrentAccountNumber() {
        return this.ACCOUNT_NUMBER_COUNTER.get();
    }

    private void incrementCounter() {
        this.ACCOUNT_NUMBER_COUNTER.set(this.ACCOUNT_NUMBER_COUNTER.incrementAndGet());
    }
}
