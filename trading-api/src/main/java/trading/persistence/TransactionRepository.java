package trading.persistence;

import trading.domain.transaction.Transaction;
import trading.domain.transaction.TransactionNumber;

public interface TransactionRepository {

    Transaction save(Transaction transaction);

    Transaction findByTransactionNumber(TransactionNumber transactionNumber);
}