package trading.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import trading.domain.Account.AccountNumber;
import trading.domain.DateTime.DateTime;
import trading.domain.transaction.InvalidTransactionNumberException;
import trading.domain.transaction.Transaction;
import trading.domain.transaction.TransactionBuy;
import trading.domain.transaction.TransactionNotFoundException;
import trading.domain.transaction.TransactionNumber;
import trading.domain.transaction.TransactionRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionRepositoryInMemory implements TransactionRepository {
    private final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(AccountHibernateDTO.class).buildSessionFactory();

    public void save(Transaction transaction) {
        Session session = this.sessionFactory.getCurrentSession();
        TransactionHibernateDTO transactionHibernateDTO = TransactionHydrator.toHibernateDto
                (transaction);
        session.beginTransaction();
        session.saveOrUpdate(transactionHibernateDTO);
        session.getTransaction().commit();
    }

    public Transaction findByTransactionNumber(TransactionNumber transactionNumber) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        UUID transactionNumberUUID = transactionNumber.getId();
        TransactionHibernateDTO transactionHibernateDTO = session.get(TransactionHibernateDTO
                .class, transactionNumberUUID);
        session.getTransaction().commit();

        if (transactionHibernateDTO == null) {
            throw new TransactionNotFoundException(transactionNumber);
        }
        return TransactionHydrator.toTransaction(transactionHibernateDTO);
    }

    public TransactionBuy findReferredTransaction(TransactionNumber transactionNumber) {
        Transaction retrievedTransaction = this.findByTransactionNumber(transactionNumber);
        if (!(retrievedTransaction instanceof TransactionBuy)) {
            throw new InvalidTransactionNumberException(transactionNumber);
        }
        if (retrievedTransaction == null) {
            throw new InvalidTransactionNumberException(transactionNumber);
        }
        return (TransactionBuy) retrievedTransaction;
    }

    public List<Transaction> findAllTransactionFromDate(AccountNumber accountNumber, DateTime reportDateTime) {
        LocalDateTime time = LocalDateTime.ofInstant(reportDateTime.toInstant(), ZoneOffset.ofHours(0));
        time = time.minus(1, ChronoUnit.DAYS);
        Instant reportDateInstantMinusOneDay = time.atZone(ZoneOffset.ofHours(0)).toInstant();

        String accountNumberAsString = accountNumber.getString();
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<TransactionHibernateDTO> transactionHibernateDTOS = session.createSQLQuery("select * from " +
                "TRANSACTIONS WHERE accountNumber= :accountNumber AND instant<= :reportDateInstant AND instant> :reportDateInstantMinusOneDay ")
                .setParameter("accountNumber", accountNumberAsString)
                .setParameter("reportDateInstant", reportDateTime.toInstant())
                .setParameter("reportDateInstantMinusOneDay", reportDateInstantMinusOneDay
                ).addEntity(TransactionHibernateDTO.class).list();
        session.getTransaction().commit();

        List<Transaction> transactions = new ArrayList<>();
        for (TransactionHibernateDTO transactionHibernateDTO : transactionHibernateDTOS) {
            transactions.add(TransactionHydrator.toTransaction(transactionHibernateDTO));
        }
        return transactions;
    }
}


