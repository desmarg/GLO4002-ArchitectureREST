package trading.persistence;

import trading.domain.Account.AccountNumber;
import trading.domain.DateTime.DateTime;
import trading.domain.transaction.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionRepositoryInMemory implements TransactionRepository {

    private final Map<TransactionNumber, Transaction> transactionMap = new HashMap<>();

    public void save(Transaction transaction) {
        this.transactionMap.put(transaction.getTransactionNumber(), transaction);
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

    public List<Transaction> findAllTransactionFromDate(AccountNumber accountNumber, DateTime date) {
        List<Transaction> returnTransactionMap = new ArrayList<>();
        for (Transaction transaction : this.transactionMap.values()) {
            if (transaction.getAccountNumber() == accountNumber && transaction.getDateTime().toInstant().compareTo(date.toInstant()) <= 0) {
                returnTransactionMap.add(transaction);
            }
        }
        return returnTransactionMap;
    }
}


