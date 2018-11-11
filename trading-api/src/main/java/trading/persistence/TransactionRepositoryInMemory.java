package trading.persistence;

import trading.domain.Transaction.*;

import java.util.HashMap;
import java.util.Map;

public class TransactionRepositoryInMemory implements TransactionRepository {

    private final Map<TransactionNumber, Transaction> transactionMap = new HashMap<>();

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

    public TransactionBuy findReferredTransaction(TransactionNumber transactionNumber) {
        Transaction retrievedTransaction = this.transactionMap.get(transactionNumber);
        if (!(retrievedTransaction instanceof TransactionBuy)) {
            throw new InvalidTransactionNumberException(transactionNumber);
        }
        if (retrievedTransaction == null) {
            throw new InvalidTransactionNumberException(transactionNumber);
        }
        return (TransactionBuy) retrievedTransaction;
    }
}


