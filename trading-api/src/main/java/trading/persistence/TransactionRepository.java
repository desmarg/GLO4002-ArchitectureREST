package trading.persistence;

import trading.domain.Account;
import trading.domain.transaction.Transaction;
import trading.domain.transaction.TransactionNumber;
import trading.exception.TransactionNotFoundException;

public interface TransactionRepository {

    Account save(Transaction transaction);

    Account findByAccountNumber(TransactionNumber transactionNumber) throws TransactionNotFoundException;

    boolean transactionAlreadyExists(TransactionNumber transactionNumber);

}