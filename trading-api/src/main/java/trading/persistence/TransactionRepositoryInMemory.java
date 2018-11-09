package trading.persistence;

import trading.domain.transaction.TransactionRepository;
import trading.domain.transaction.Transaction;
import trading.domain.transaction.TransactionNumber;
import trading.domain.transaction.TransactionNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class TransactionRepositoryInMemory implements TransactionRepository {

    private Map<TransactionNumber, Transaction> transactionMap = new HashMap<>();

    public Transaction save(Transaction transaction) {
        this.transactionMap.put(transaction.getTransactionNumber(), transaction);
        return transaction;
    }

    public Transaction findByTransactionNumber(TransactionNumber transactionNumber) {
        Transaction retrievedTransaction = this.transactionMap.get(transactionNumber);
        if (retrievedTransaction != null) {
            return retrievedTransaction;
        }
        throw new TransactionNotFoundException(transactionNumber);
    }
}


