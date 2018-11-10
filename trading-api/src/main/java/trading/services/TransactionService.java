package trading.services;

import trading.api.request.TransactionPostRequestDTO;
import trading.domain.Account.Account;
import trading.domain.Account.AccountNumber;
import trading.domain.transaction.StockParametersDontMatchException;
import trading.domain.transaction.Transaction;
import trading.domain.transaction.TransactionBuy;
import trading.domain.transaction.TransactionBuyAssembler;
import trading.domain.transaction.TransactionNumber;
import trading.domain.transaction.TransactionRepository;
import trading.domain.transaction.TransactionSell;
import trading.domain.transaction.TransactionSellAssembler;
import trading.external.response.Market.MarketClosedException;

public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final StockService stockService;
    private final MarketService marketService;
    private final AccountService accountService;

    public TransactionService(TransactionRepository transactionRepository, StockService stockService, MarketService marketService, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.stockService = stockService;
        this.marketService = marketService;
        this.accountService = accountService;
    }

    public Transaction executeTransactionBuy(String accountNumber, TransactionPostRequestDTO transactionPostRequestDTO) {
        Account account = this.accountService.findByAccountNumber(new AccountNumber(accountNumber));
        TransactionBuy transactionBuy = TransactionBuyAssembler.fromDTO(transactionPostRequestDTO, account.getAccountNumber(), this.stockService);
        this.validateMarketIsOpen(transactionBuy);
        transactionBuy.executeTransaction(account);
        this.transactionRepository.save(transactionBuy);
        return transactionBuy;
    }

    public Transaction executeTransactionSell(String accountNumber, TransactionPostRequestDTO transactionPostRequestDTO) {
        Account account = this.accountService.findByAccountNumber(new AccountNumber(accountNumber));
        TransactionSell transactionSell = TransactionSellAssembler.fromDTO(transactionPostRequestDTO, account.getAccountNumber(), this.stockService);
        this.validateMarketIsOpen(transactionSell);
        TransactionBuy referredTransaction = this.getReferredTransaction(transactionSell.getReferredTransactionNumber());
        this.validateStockIsFromAccount(account, referredTransaction);
        transactionSell.executeTransaction(account);
        this.deduceReferredTransactionStocks(referredTransaction, transactionSell);
        this.transactionRepository.save(transactionSell);
        return transactionSell;
    }

    private void validateStockIsFromAccount(Account account, TransactionBuy referredTransaction) {
        if (referredTransaction.getAccountNumber() != account.getAccountNumber()) {
            throw new RuntimeException("The account does not possess this stock...");
        }
    }

    private void validateMarketIsOpen(Transaction transaction) {
        String market = transaction.getMarket();
        if (this.marketService.isMarketOpen(market)) {
            throw new MarketClosedException(market);
        }
    }

    public Transaction getTransaction(TransactionNumber transactionNumber) {
        return this.transactionRepository.findByTransactionNumber(transactionNumber);
    }


    private TransactionBuy getReferredTransaction(TransactionNumber transactionNumber) {

        Transaction transaction = this.transactionRepository.findByTransactionNumber(transactionNumber);
        if (!(transaction instanceof TransactionBuy)) {
            throw new StockParametersDontMatchException();
        }
        return (TransactionBuy) transaction;
    }

    public void deduceReferredTransactionStocks(TransactionBuy referredTransaction, TransactionSell transactionSell) {
        referredTransaction.deduceStock(transactionSell.getQuantity());
        this.transactionRepository.save(referredTransaction);
    }
}