package trading.services;

import trading.api.request.TransactionPostRequestDTO;
import trading.domain.Account.Account;
import trading.domain.Credits.Credits;
import trading.domain.DateTime.DateTime;
import trading.domain.DateTime.DateTimeParser;
import trading.domain.Report.Portfolio;
import trading.domain.Report.Report;
import trading.domain.Report.ReportType;
import trading.domain.transaction.*;
import trading.external.response.Market.MarketClosedException;

import java.util.List;

public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final StockService stockService;
    private final MarketService marketService;
    private final AccountService accountService;
    private final PortfolioService portfolioService;

    public TransactionService(TransactionRepository transactionRepository, StockService stockService, MarketService marketService, AccountService accountService, PortfolioService portfolioService) {
        this.transactionRepository = transactionRepository;
        this.stockService = stockService;
        this.marketService = marketService;
        this.accountService = accountService;
        this.portfolioService = portfolioService;
    }

    public Transaction executeTransactionBuy(String accountNumber, TransactionPostRequestDTO transactionPostRequestDTO) {
        Account account = this.accountService.findByAccountNumber(accountNumber);
        Credits stockPrice = this.stockService.retrieveStockPrice(transactionPostRequestDTO.stock, new DateTime(transactionPostRequestDTO.date));
        TransactionBuy transactionBuy = TransactionBuyAssembler.fromDTO(transactionPostRequestDTO, account.getAccountNumber(), stockPrice);
        this.validateMarketIsOpen(transactionBuy);
        account.buyTransaction(transactionBuy);
        this.accountService.update(account);
        this.transactionRepository.save(transactionBuy);

        return transactionBuy;
    }

    public Transaction executeTransactionSell(String accountNumber, TransactionPostRequestDTO transactionPostRequestDTO) {
        Account account = this.accountService.findByAccountNumber(accountNumber);
        Credits stockPrice = this.stockService.retrieveStockPrice(transactionPostRequestDTO.stock, new DateTime(transactionPostRequestDTO.date));
        TransactionSell transactionSell = TransactionSellAssembler.fromDTO(transactionPostRequestDTO, account.getAccountNumber(), stockPrice);
        this.validateMarketIsOpen(transactionSell);
        TransactionBuy referredTransaction = this.getReferredTransaction(transactionSell.getReferredTransactionNumber());
        account.sellTransaction(transactionSell, referredTransaction);
        this.accountService.update(account);
        this.transactionRepository.save(transactionSell);

        return transactionSell;
    }


    private void validateMarketIsOpen(Transaction transaction) {
        String market = transaction.getMarket();
        if (this.marketService.isMarketOpenAtHour(market, transaction.getDateTime())) {
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

    public Report getReportFromDate(String accountNumber, String date, String reportType) {
        Account account = this.accountService.findByAccountNumber(accountNumber);
        DateTime reportDate = DateTimeParser.createFromReportDate(date);
        ReportType.fromString(reportType);
        List<Transaction> transactionList = this.transactionRepository.findAllTransactionAtDate(account.getAccountNumber(), reportDate);
        List<TransactionBuy> transactionBuyHistory = this.transactionRepository.findTransactionBuyBeforeDate(account.getAccountNumber(), reportDate);
        List<TransactionSell> transactionSellHistory = this.transactionRepository.findTransactionSellBeforeDate(account.getAccountNumber(), reportDate);
        Portfolio portfolio = this.portfolioService.getPortfolioValue(account, reportDate, transactionBuyHistory, transactionSellHistory);
        Report report = new Report(reportDate, transactionList, portfolio.getAccountValue(), portfolio.getPortfolioValue());
        return report;
    }
}