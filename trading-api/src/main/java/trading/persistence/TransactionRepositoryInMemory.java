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

import java.util.ArrayList;
import java.util.List;

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
        String transactionNumberAsString = transactionNumber.getStringUUID();
        TransactionHibernateDTO transactionHibernateDTO = session.get(TransactionHibernateDTO
                .class, transactionNumberAsString);
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

    public List<Transaction> findAllTransactionFromDate(AccountNumber accountNumber, DateTime date) {
        String accountNumberAsString = accountNumber.getId();
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<TransactionHibernateDTO> transactionHibernateDTOS = session.createSQLQuery("select * from " +
                "TRANSACTIONS WHERE accountNumber= :accountNumber AND dateTime= :dateTime")
                .setParameter
                        ("accountNumber", accountNumberAsString).setParameter("dateTime", date
                        .toDate()).list();

        List<Transaction> transactions = new ArrayList<>();
        for (TransactionHibernateDTO transactionHibernateDTO : transactionHibernateDTOS) {
            transactions.add(TransactionHydrator.toTransaction(transactionHibernateDTO));
        }
        return transactions;
    }
}


