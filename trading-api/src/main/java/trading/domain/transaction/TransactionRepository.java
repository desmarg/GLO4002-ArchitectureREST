package trading.domain.transaction;

import trading.domain.Account.AccountNumber;
import trading.domain.DateTime.DateTime;

import java.util.List;

public interface TransactionRepository {

    void save(Transaction transaction);

    Transaction findByTransactionNumber(TransactionNumber transactionNumber);

    TransactionBuy findReferredTransaction(TransactionNumber transactionNumber);

    List<Transaction> findAllTransactionFromDate(AccountNumber accountNumber, DateTime date);

}