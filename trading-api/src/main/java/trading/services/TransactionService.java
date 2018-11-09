package trading.services;

import application.market.MarketService;
import trading.domain.Account;
import trading.domain.transaction.Transaction;
import trading.domain.transaction.TransactionBuy;
import trading.domain.transaction.TransactionNumber;
import trading.domain.transaction.TransactionSell;
import trading.exception.StockParametersDontMatchException;
import trading.persistence.TransactionRepository;

public class TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction executeTransactionBuy(Account account, TransactionBuy transactionBuy) {

        transactionBuy.executeTransaction(account);
        this.transactionRepository.save(transactionBuy);
        return transactionBuy;
    }

    public Transaction executeTransactionSell(Account account, TransactionSell transactionSell) {
        TransactionBuy referredTransaction = this.getReferredTransaction(transactionSell.getReferredTransactionNumber());
        transactionSell.executeTransaction(account, referredTransaction);
        this.deduceStocks(referredTransaction.getTransactionNumber(), transactionSell.getQuantity());
        this.transactionRepository.save(transactionSell);
        return transactionSell;
    }

    public Transaction getTransaction(TransactionNumber transactionNumber) {
        return this.transactionRepository.findByTransactionNumber(transactionNumber);
    }


    public TransactionBuy getReferredTransaction(TransactionNumber transactionNumber) {

        Transaction transaction = this.transactionRepository.findByTransactionNumber(transactionNumber);
        if (!(transaction instanceof TransactionBuy)) {
            throw new StockParametersDontMatchException();
        }
        return (TransactionBuy) transaction;
    }

    public void deduceStocks(TransactionNumber referredTransactionNumber, Long quantity) {
        TransactionBuy referredTransaction = this.getReferredTransaction(referredTransactionNumber);
        referredTransaction.deduceStock(quantity);
        this.transactionRepository.save(referredTransaction);
    }
}