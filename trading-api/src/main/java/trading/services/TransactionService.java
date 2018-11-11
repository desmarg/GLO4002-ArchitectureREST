package trading.services;

import trading.api.request.TransactionPostRequestDTO;
import trading.domain.Account.Account;
import trading.domain.Account.AccountNumber;
import trading.domain.Transaction.*;
import trading.external.response.Market.MarketClosedException;

public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletService walletService;
    private final StockService stockService;
    private final MarketService marketService;
    private final AccountService accountService;

    public TransactionService(TransactionRepository transactionRepository,
                              WalletService walletService,
                              StockService stockService,
                              MarketService marketService,
                              AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.walletService = walletService;
        this.stockService = stockService;
        this.marketService = marketService;
        this.accountService = accountService;
    }

    public Transaction executeTransactionBuy(String accountNumber, TransactionPostRequestDTO transactionPostRequestDTO) {
        Account account = this.accountService.findByAccountNumber(new AccountNumber(accountNumber));
        TransactionBuy transactionBuy = TransactionBuyAssembler.fromDTO(transactionPostRequestDTO, account.getAccountNumber(), this.stockService);
        this.validateMarketIsOpen(transactionBuy);
        account.buyTransaction(transactionBuy);
        this.transactionRepository.save(transactionBuy);
        this.walletService.update(account, transactionBuy);
        return transactionBuy;
    }

    public Transaction executeTransactionSell(String accountNumber, TransactionPostRequestDTO transactionPostRequestDTO) {
        Account account = this.accountService.findByAccountNumber(new AccountNumber(accountNumber));
        TransactionSell transactionSell = TransactionSellAssembler.fromDTO(transactionPostRequestDTO, account.getAccountNumber(), this.stockService);
        this.validateMarketIsOpen(transactionSell);
        TransactionBuy referredTransaction = this.getReferredTransaction(transactionSell.getReferredTransactionNumber());
        this.validateStockIsFromAccount(account, referredTransaction);
        this.validateEnoughStock(referredTransaction, transactionSell);
        account.sellTransaction(transactionSell);
        this.deduceReferredTransactionStocks(referredTransaction, transactionSell);
        this.transactionRepository.save(transactionSell);
        return transactionSell;
    }

    private void validateEnoughStock(TransactionBuy referredTransaction, TransactionSell transactionSell) {
        if (referredTransaction.getRemainingStocks() < transactionSell.getQuantity()) {
            throw new NotEnoughStockException(transactionSell.getStock());
        }
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
        TransactionBuy referredTransaction = this.transactionRepository.findReferredTransaction(transactionNumber);
        return referredTransaction;
    }

    public void deduceReferredTransactionStocks(TransactionBuy referredTransaction, TransactionSell transactionSell) {
        referredTransaction.deduceStock(transactionSell.getQuantity());
        this.transactionRepository.save(referredTransaction);
    }
}