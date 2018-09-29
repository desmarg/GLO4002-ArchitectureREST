package application;

import domain.account.Account;
import domain.transaction.Transaction;
import domain.transaction.TransactionId;

public class TransactionService {

    public TransactionService() {
    }

    public static void makeTransaction(Account account, Transaction transaction) {
        account.makeTransaction(transaction);
    }

    public Transaction getTransaction(Account account, TransactionId transactionId) {
        return account.getTransaction(transactionId);
    }
}
