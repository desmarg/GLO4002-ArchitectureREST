package application;

import domain.account.Account;
import domain.transaction.Transaction;
import domain.transaction.TransactionNumber;
import domain.transaction.TransactionType;

public class TransactionService {

    public TransactionService() {
    }

    public static void makeTransaction(Account account, Transaction transaction) {
        if(transaction.getTransactionType() == TransactionType.BUY){
            account.buyTransaction(transaction);
        }
        else if(transaction.getTransactionType() == TransactionType.SELL){
            account.sellTransaction(transaction);
        }
    }

    public Transaction getTransactionFromAccount(Account account, TransactionNumber transactionNumber) {
        return account.getTransactionFromAll(transactionNumber);
    }
}
