package trading.services;

import trading.api.request.TransactionPostRequestDTO;
import trading.domain.Credits;
import trading.domain.Currency;
import trading.domain.ForeignExchangeRepository;
import trading.domain.account.Account;
import trading.domain.datetime.DateTime;
import trading.domain.datetime.InvalidDateException;
import trading.domain.datetime.MissingDateException;
import trading.domain.report.Portfolio;
import trading.domain.report.Report;
import trading.domain.report.ReportType;
import trading.domain.transaction.*;
import trading.external.response.market.MarketClosedException;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.TimeZone;

public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final StockService stockService;
    private final MarketService marketService;
    private final AccountService accountService;
    private final ReportService reportService;
    private final ForeignExchangeRepository forexRepo;

    public TransactionService(TransactionRepository transactionRepository,
                              StockService stockService, MarketService marketService,
                              AccountService accountService, ReportService reportService, ForeignExchangeRepository forexRepo) {
        this.transactionRepository = transactionRepository;
        this.stockService = stockService;
        this.marketService = marketService;
        this.accountService = accountService;
        this.reportService = reportService;
        this.forexRepo = forexRepo;
    }

    public Transaction executeTransactionBuy(String accountNumber, TransactionPostRequestDTO transactionPostRequestDTO) {
        Account account = this.accountService.findByAccountNumber(accountNumber);
        Credits stockPrice = this.stockService.retrieveStockPrice(
                transactionPostRequestDTO.stock,
                new DateTime(transactionPostRequestDTO.date)
        );
        TransactionBuy transactionBuy = TransactionBuyAssembler.fromDTO(
                transactionPostRequestDTO,
                account.getAccountNumber(),
                stockPrice
        );
        this.validateMarketIsOpen(transactionBuy);
        account.buyTransaction(transactionBuy);
        this.accountService.update(account);
        this.transactionRepository.save(transactionBuy);

        return transactionBuy;
    }

    public Transaction executeTransactionSell(
            String accountNumber,
            TransactionPostRequestDTO transactionPostRequestDTO
    ) {
        Account account = this.accountService.findByAccountNumber(accountNumber);
        Credits stockPrice = this.stockService.retrieveStockPrice(
                transactionPostRequestDTO.stock,
                new DateTime(transactionPostRequestDTO.date)
        );
        TransactionSell transactionSell =
                TransactionSellAssembler.fromDTO(transactionPostRequestDTO,
                        account.getAccountNumber(), stockPrice);
        this.validateMarketIsOpen(transactionSell);
        TransactionBuy referredTransaction = this.transactionRepository
                .findReferredTransaction(transactionSell.getReferredTransactionNumber());
        account.sellTransaction(transactionSell, referredTransaction);
        this.accountService.update(account);
        this.transactionRepository.save(transactionSell);

        return transactionSell;
    }


    private void validateMarketIsOpen(Transaction transaction) {
        String market = transaction.getMarket();
        if (!this.marketService.isMarketOpenAtHour(market, transaction.getDateTime())) {
            throw new MarketClosedException(transaction);
        }
    }

    public Transaction getTransaction(TransactionNumber transactionNumber) {
        return this.transactionRepository.findByTransactionNumber(transactionNumber);
    }

    public Report getReportFromDate(String accountNumber, String date, String reportType) {
        Account account = this.accountService.findByAccountNumber(accountNumber);
        DateTime reportDate = new DateTime(this.stringToInstantParser(date));
        ReportType.fromString(reportType);
        List<TransactionBuy> transactionBuyHistory = this.transactionRepository
                .findTransactionBuyBeforeDate(account.getAccountNumber(), reportDate);
        List<TransactionSell> transactionSellHistory = this.transactionRepository
                .findTransactionSellBeforeDate(account.getAccountNumber(), reportDate);
        List<Transaction> transactionList = this.transactionRepository
                .findAllTransactionAtDate(account.getAccountNumber(), reportDate);
        Portfolio portfolio = this.reportService.getPortfolio(account.getInitialCredits(), reportDate, transactionBuyHistory, transactionSellHistory, this.forexRepo);
        return new Report(reportDate, transactionList, portfolio.accountCredits,
                portfolio.portfolioValue);
    }

    private Instant stringToInstantParser(String date) {
        if (date == null) {
            throw new MissingDateException();
        }
        TimeZone timeZone = TimeZone.getDefault();
        String instantString = date.concat(" 23:59:59.999");
        DateTimeFormatter dateTimeFormatter
                = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime localDateTime = this.parseDate(instantString, dateTimeFormatter);
        ZonedDateTime zonedDateTime = localDateTime.atZone(timeZone.toZoneId());
        Instant instant = zonedDateTime.toInstant();
        if ((instant.truncatedTo(ChronoUnit.DAYS))
                .compareTo(Instant.now().truncatedTo(ChronoUnit.DAYS)) >= 0) {
            throw new InvalidDateException();
        }
        return instant;
    }

    private LocalDateTime parseDate(String instantString, DateTimeFormatter dateTimeFormatter) {
        try {
            return LocalDateTime.parse(instantString, dateTimeFormatter);
        } catch (Exception e) {
            throw new InvalidDateException();
        }
    }
}