package trading.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import trading.domain.account.AccountNumber;
import trading.domain.datetime.DateTime;
import trading.domain.transaction.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionRepositoryHibernate implements TransactionRepository {
    private final SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(AccountHibernateDTO.class)
            .buildSessionFactory();

    public void save(Transaction transaction) {
        Session session = this.sessionFactory.getCurrentSession();
        TransactionHibernateDTO transactionHibernateDTO =
                TransactionHydrator.toHibernateDto(transaction);
        session.beginTransaction();
        session.saveOrUpdate(transactionHibernateDTO);
        session.getTransaction().commit();
    }

    public Transaction findByTransactionNumber(TransactionNumber transactionNumber) {
        TransactionHibernateDTO transactionHibernateDTO =
                this.findTransactionDTO(transactionNumber);
        if (transactionHibernateDTO == null) {
            throw new TransactionNotFoundException(transactionNumber);
        }
        return TransactionHydrator.toTransaction(transactionHibernateDTO);
    }

    public TransactionBuy findReferredTransaction(TransactionNumber transactionNumber) {
        TransactionHibernateDTO transactionHibernateDTO =
                this.findTransactionDTO(transactionNumber);
        if (transactionHibernateDTO == null) {
            throw new InvalidTransactionNumberException();
        }
        return TransactionHydrator.toTransactionBuy(transactionHibernateDTO);
    }

    private TransactionHibernateDTO findTransactionDTO(TransactionNumber transactionNumber) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        UUID transactionNumberUUID = transactionNumber.getId();
        TransactionHibernateDTO transactionHibernateDTO =
                session.get(TransactionHibernateDTO.class, transactionNumberUUID);
        session.getTransaction().commit();

        return transactionHibernateDTO;
    }

    public List<Transaction> findAllTransactionAtDate(AccountNumber accountNumber,
                                                      DateTime reportDateTime) {
        LocalDateTime time = LocalDateTime.ofInstant(reportDateTime.toInstant(), ZoneOffset.ofHours(0));
        time = time.minus(1, ChronoUnit.DAYS);
        Instant reportDateInstantMinusOneDay = time.atZone(ZoneOffset.ofHours(0)).toInstant();

        String accountNumberAsString = accountNumber.getString();
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<TransactionHibernateDTO> transactionHibernateDTOS = session
                .createSQLQuery("select * from TRANSACTIONS "
                        + "WHERE accountNumber= :accountNumber "
                        + "AND instant<= :reportDateInstant "
                        + "AND instant> :reportDateInstantMinusOneDay")
                .setParameter("accountNumber", accountNumberAsString)
                .setParameter("reportDateInstantMinusOneDay", reportDateInstantMinusOneDay)
                .setParameter("reportDateInstant", reportDateTime.toInstant())
                .addEntity(TransactionHibernateDTO.class).list();
        session.getTransaction().commit();

        List<Transaction> transactions = new ArrayList<>();
        for (TransactionHibernateDTO transactionHibernateDTO : transactionHibernateDTOS) {
            transactions.add(TransactionHydrator.toTransaction(transactionHibernateDTO));
        }
        return transactions;
    }

    public List<TransactionBuy> findTransactionBuyBeforeDate(AccountNumber accountNumber,
                                                             DateTime reportDateTime) {
        Instant beginningOfDay = reportDateTime.toInstantTruncatedToDay();
        Instant beginningOfNextDay = beginningOfDay.plus(1, ChronoUnit.DAYS);
        String accountNumberAsString = accountNumber.getString();
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<TransactionHibernateDTO> transactionHibernateDTOS = session
                .createSQLQuery("select * from TRANSACTIONS "
                        + "WHERE accountNumber= :accountNumber "
                        + "AND instant < :reportDateInstant "
                        + "AND transactionType= :myTransactionType")
                .setParameter("accountNumber", accountNumberAsString)
                .setParameter("reportDateInstant", reportDateTime.toInstant())
                .setParameter("myTransactionType", TransactionType.BUY.toString())
                .addEntity(TransactionHibernateDTO.class).list();
        session.getTransaction().commit();

        List<TransactionBuy> transactions = new ArrayList<>();
        for (TransactionHibernateDTO transactionHibernateDTO : transactionHibernateDTOS) {
            transactions.add(TransactionHydrator.toTransactionBuy(transactionHibernateDTO));
        }
        return transactions;
    }

    public List<TransactionSell> findTransactionSellBeforeDate(AccountNumber accountNumber,
                                                               DateTime reportDateTime) {
        Instant beginningOfDay = reportDateTime.toInstantTruncatedToDay();
        Instant beginningOfNextDay = beginningOfDay.plus(1, ChronoUnit.DAYS);
        String accountNumberAsString = accountNumber.getString();
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<TransactionHibernateDTO> transactionHibernateDTOS = session
                .createSQLQuery("select * from TRANSACTIONS "
                        + "WHERE accountNumber= :accountNumber "
                        + "AND instant < :reportDateInstant "
                        + "AND transactionType= :transactionType")
                .setParameter("accountNumber", accountNumberAsString)
                .setParameter("reportDateInstant", reportDateTime.toInstant())
                .setParameter("transactionType", TransactionType.SELL.toString())
                .addEntity(TransactionHibernateDTO.class).list();
        session.getTransaction().commit();

        List<TransactionSell> transactions = new ArrayList<>();
        for (TransactionHibernateDTO transactionHibernateDTO : transactionHibernateDTOS) {
            transactions.add(TransactionHydrator.toTransactionSell(transactionHibernateDTO));
        }
        return transactions;
    }

}

