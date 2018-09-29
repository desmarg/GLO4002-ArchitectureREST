package application;

import domain.Account;
import domain.Transaction;

public class TransactionService {

        public TransactionService(){}

        public static void makeTransaction(Account account, Transaction transaction){
                account.makeTransaction(transaction);
        }
}
