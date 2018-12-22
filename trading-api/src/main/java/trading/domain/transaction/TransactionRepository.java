package trading.domain.transaction;

import trading.domain.account.AccountNumber;
import trading.domain.datetime.DateTime;

import java.util.List;

public interface TransactionRepository {

    void save(Transaction transaction);

    Transaction findByTransactionNumber(TransactionID transactionID);

    TransactionBuy findReferredTransaction(TransactionID transactionID);

    List<Transaction> findAllTransactionAtDate(AccountNumber accountNumber, DateTime date);

    List<TransactionBuy> findTransactionBuyBeforeDate(AccountNumber accountNumber,
                                                      DateTime dateTime);

    List<TransactionSell> findTransactionSellBeforeDate(AccountNumber accountNumber,
                                                        DateTime dateTime);

}