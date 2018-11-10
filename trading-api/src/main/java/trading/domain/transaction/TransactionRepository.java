package trading.domain.transaction;

public interface TransactionRepository {

    Transaction save(Transaction transaction);

    Transaction findByTransactionNumber(TransactionNumber transactionNumber);

    TransactionBuy findReferredTransaction(TransactionNumber transactionNumber);

}