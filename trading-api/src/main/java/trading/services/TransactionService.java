package trading.services;

import trading.domain.Account;
import trading.domain.Transaction;
import trading.domain.TransactionNumber;

public class TransactionService {

    public TransactionService() {
    }

    public static void makeTransaction(Account account, Transaction transaction) {
        transaction.make(account);
    }

    public Transaction getTransactionFromAccount(Account account, TransactionNumber transactionNumber) {
        return account.getTransaction(transactionNumber);
    }
}
