package trading.application.services;

import trading.api.request.TransactionPostRequestDTO;
import trading.domain.Credits;
import trading.domain.ForeignExchangeRepository;
import trading.domain.MarketRepository;
import trading.domain.StockRepository;
import trading.domain.account.Account;
import trading.domain.account.AccountNumber;
import trading.domain.account.AccountRepository;
import trading.domain.datetime.DateTime;
import trading.domain.datetime.InvalidDateException;
import trading.domain.datetime.MissingDateException;
import trading.domain.report.Report;
import trading.domain.report.ReportType;
import trading.domain.transaction.*;
import trading.external.response.market.MarketClosedException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.TimeZone;

public class TransactionApplicationService {

    private final TransactionRepository transactionRepository;
    private final StockRepository stockRepository;
    private final MarketRepository marketRepository;
    private final AccountRepository accountRepository;
    private final ForeignExchangeRepository forexRepo;

    public TransactionApplicationService(TransactionRepository transactionRepository,
                                         StockRepository stockRepository, MarketRepository marketRepository,
                                         AccountRepository accountRepository, ForeignExchangeRepository forexRepo) {
        this.transactionRepository = transactionRepository;
        this.stockRepository = stockRepository;
        this.marketRepository = marketRepository;
        this.accountRepository = accountRepository;
        this.forexRepo = forexRepo;
    }

    public Transaction executeTransactionBuy(String accountNumber, TransactionPostRequestDTO transactionPostRequestDTO) {
        Account account = this.accountRepository.findByAccountNumber(new AccountNumber(accountNumber));
        Credits stockPrice = this.stockRepository.retrieveStockPrice(
                transactionPostRequestDTO.stock,
                new DateTime(transactionPostRequestDTO.date),
                false
        );
        TransactionBuy transactionBuy = TransactionBuyAssembler.fromDTO(
                transactionPostRequestDTO,
                account.getAccountNumber(),
                stockPrice
        );
        this.validateMarketIsOpen(transactionBuy);
        account.buyTransaction(transactionBuy);
        this.accountRepository.update(account);
        this.transactionRepository.save(transactionBuy);

        return transactionBuy;
    }

    public Transaction executeTransactionSell(
            String accountNumber,
            TransactionPostRequestDTO transactionPostRequestDTO
    ) {
        Account account = this.accountRepository.findByAccountNumber(new AccountNumber(accountNumber));
        Credits stockPrice = this.stockRepository.retrieveStockPrice(
                transactionPostRequestDTO.stock,
                new DateTime(transactionPostRequestDTO.date),
                false
        );
        TransactionSell transactionSell =
                TransactionSellAssembler.fromDTO(transactionPostRequestDTO,
                        account.getAccountNumber(), stockPrice);
        this.validateMarketIsOpen(transactionSell);
        TransactionBuy referredTransaction = this.transactionRepository
                .findReferredTransaction(transactionSell.getReferredTransactionID());
        account.sellTransaction(transactionSell, referredTransaction);
        this.accountRepository.update(account);
        this.transactionRepository.save(transactionSell);

        return transactionSell;
    }


    private void validateMarketIsOpen(Transaction transaction) {
        String market = transaction.getMarket();
        if (!this.marketRepository.isMarketOpenAtHour(market, transaction.getDateTime())) {
            throw new MarketClosedException(transaction);
        }
    }

    public Transaction getTransaction(TransactionID transactionID) {
        return this.transactionRepository.findByTransactionNumber(transactionID);
    }

    public Report getAccountReportFromDate(String accountNumber, String date, String reportType) {
        Account account = this.accountRepository.findByAccountNumber(new AccountNumber(accountNumber));
        DateTime reportDate = new DateTime(this.stringToInstantParser(date));
        ReportType.validateReportType(reportType);
        List<TransactionBuy> transactionBuyHistory = this.transactionRepository
                .findTransactionBuyBeforeDate(account.getAccountNumber(), reportDate);
        List<TransactionSell> transactionSellHistory = this.transactionRepository
                .findTransactionSellBeforeDate(account.getAccountNumber(), reportDate);
        List<Transaction> transactionList = this.transactionRepository
                .findAllTransactionAtDate(account.getAccountNumber(), reportDate);
        return new Report(reportDate, transactionList, account.getInitialCredits(), transactionBuyHistory, transactionSellHistory, this.forexRepo, this.stockRepository);
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