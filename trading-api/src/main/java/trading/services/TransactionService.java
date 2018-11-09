package trading.services;

import application.market.MarketService;
import exception.MarketClosedException;
import trading.api.request.TransactionPostRequestDTO;
import trading.domain.Account;
import trading.domain.transaction.*;
import trading.exception.StockParametersDontMatchException;
import trading.persistence.TransactionRepository;

public class TransactionService {

    private TransactionRepository transactionRepository;
    private StockService stockService;
    private MarketService marketService;

    public TransactionService(TransactionRepository transactionRepository, StockService stockService, MarketService marketService) {
        this.transactionRepository = transactionRepository;
        this.stockService = stockService;
        this.marketService = marketService;
    }

    public Transaction executeTransactionBuy(Account account, TransactionPostRequestDTO transactionPostRequestDTO) {
        TransactionBuy transactionBuy = TransactionBuyAssembler.fromDTO(transactionPostRequestDTO, this.stockService);
        if(transactionBuy.isMarketOpen(this.marketService)){
            throw new MarketClosedException();
        }
        transactionBuy.executeTransaction(account);
        this.transactionRepository.save(transactionBuy);
        return transactionBuy;
    }

    public Transaction executeTransactionSell(Account account, TransactionPostRequestDTO transactionPostRequestDTO) {
        TransactionSell transactionSell = TransactionSellAssembler.fromDTO(transactionPostRequestDTO, this.stockService);
        TransactionBuy referredTransaction = this.getReferredTransaction(transactionSell.getReferredTransactionNumber());
        if(transactionSell.isMarketOpen(this.marketService)){
            throw new MarketClosedException();
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