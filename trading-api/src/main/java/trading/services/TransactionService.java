package trading.services;

import trading.api.request.TransactionPostRequestDTO;
import trading.domain.Account.Account;
import trading.domain.transaction.*;
import trading.domain.transaction.StockParametersDontMatchException;
import trading.external.response.Market.MarketClosedException;
import trading.domain.transaction.TransactionRepository;

public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final StockService stockService;
    private final MarketService marketService;

    public TransactionService(TransactionRepository transactionRepository, StockService stockService, MarketService marketService) {
        this.transactionRepository = transactionRepository;
        this.stockService = stockService;
        this.marketService = marketService;
    }

    public Transaction executeTransactionBuy(Account account, TransactionPostRequestDTO transactionPostRequestDTO) {
        TransactionBuy transactionBuy = TransactionBuyAssembler.fromDTO(transactionPostRequestDTO, account.getAccountNumber(), this.stockService);
        String market = transactionBuy.getMarket();
        if (this.marketService.isMarketOpen(market)) {
            throw new MarketClosedException(market);
        }
        transactionBuy.executeTransaction(account);
        this.transactionRepository.save(transactionBuy);
        return transactionBuy;
    }

    public Transaction executeTransactionSell(Account account, TransactionPostRequestDTO transactionPostRequestDTO) {
        TransactionSell transactionSell = TransactionSellAssembler.fromDTO(transactionPostRequestDTO, account.getAccountNumber(), this.stockService);
        TransactionBuy referredTransaction = this.getReferredTransaction(transactionSell.getReferredTransactionNumber());
        String market = transactionSell.getMarket();
        if (this.marketService.isMarketOpen(market)) {
            throw new MarketClosedException(market);
        }
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