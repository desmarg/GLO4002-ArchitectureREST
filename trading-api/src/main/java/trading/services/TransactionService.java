package trading.services;

import trading.api.request.TransactionPostRequestDTO;
import trading.domain.Account.Account;
import trading.domain.Account.AccountNumber;
import trading.domain.DateTime.DateTime;
import trading.domain.Report.Portfolio;
import trading.domain.Report.Report;
import trading.domain.Report.ReportType;
import trading.domain.transaction.Transaction;
import trading.domain.transaction.TransactionBuy;
import trading.domain.transaction.TransactionBuyAssembler;
import trading.domain.transaction.TransactionNumber;
import trading.domain.transaction.TransactionRepository;
import trading.domain.transaction.TransactionSell;
import trading.domain.transaction.TransactionSellAssembler;
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
        Account account = this.accountService.findByAccountNumber(new AccountNumber(accountNumber));
        TransactionBuy transactionBuy = TransactionBuyAssembler.fromDTO(transactionPostRequestDTO, account.getAccountNumber(), this.stockService);
        this.validateMarketIsOpen(transactionBuy);
        account.buyTransaction(transactionBuy);
        this.accountService.update(account);
        this.transactionRepository.save(transactionBuy);
        return transactionBuy;
    }

    public Transaction executeTransactionSell(String accountNumber, TransactionPostRequestDTO transactionPostRequestDTO) {
        Account account = this.accountService.findByAccountNumber(new AccountNumber(accountNumber));
        TransactionSell transactionSell = TransactionSellAssembler.fromDTO(transactionPostRequestDTO, account.getAccountNumber(), this.stockService);
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

    public Report getReportFromDate(Account account, DateTime date, String reportType) {
        ReportType.fromString(reportType);
        List<Transaction> transactionList = this.transactionRepository.findAllTransactionFromDate(account.getAccountNumber(), date);
        Report report = new Report(date, transactionList);
        return report;
    }

    public Portfolio getPortfolioFromDate(Account account, DateTime date, String reportType) {
        ReportType.fromString(reportType);
        List<Transaction> transactionHistory = this.transactionRepository.findAllTransactionFromDate(account.getAccountNumber(), date);
        Portfolio portfolio = this.portfolioService.fromTransactionHistory(transactionHistory, date, this.stockService);
        return portfolio;
    }
}