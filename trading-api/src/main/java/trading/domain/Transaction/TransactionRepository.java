package trading.domain.Transaction;

public interface TransactionRepository {

    Transaction save(Transaction transaction);

    Transaction findByTransactionNumber(TransactionNumber transactionNumber);

    TransactionBuy findReferredTransaction(TransactionNumber transactionNumber);

}