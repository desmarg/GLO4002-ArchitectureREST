package trading.domain.report;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import trading.domain.Credits;
import trading.domain.Currency;
import trading.domain.Stock;
import trading.domain.account.AccountNumber;
import trading.domain.datetime.DateTime;
import trading.domain.report.Report;
import trading.domain.transaction.Transaction;
import trading.domain.transaction.TransactionBuy;
import trading.domain.transaction.TransactionSell;
import trading.persistence.BasicForexRates;
import trading.persistence.StockAPIRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReportTest {
    private final DateTime VALID_DATETIME = new DateTime(Instant.parse("2000-08-04T05:00:00Z"));
    private static HashMap<Currency, Credits> INITIAL_CREDITS = new HashMap<>();
    private static HashMap<Currency, Credits> FINAL_CREDITS = new HashMap<>();
    private static HashMap<Currency, Credits> FINAL_CREDITS_SOLD = new HashMap<>();
    private static final Credits STOCK_VALUE_CREDITS = Credits.fromInteger(10, Currency.CAD);
    private static final Credits STOCK_VALUE_CREDITS_TRIPLE = Credits.fromInteger(30, Currency.CAD);
    private static final Credits STOCK_VALUE_CREDITS_QUADRUPLE = Credits.fromInteger(40, Currency.CAD);
    private static final Credits EMPTY_CREDITS = Credits.fromInteger(0, Currency.CAD);
    private static final long NUMBER_STOCK = 2L;
    private static final long NUMBER_STOCK_SOLD = 1L;
    private static final String STOCK_NAME = "ABC";
    private static final AccountNumber accountNumber = new AccountNumber("TD-0001");
    private static final BasicForexRates forexRepo = new BasicForexRates();

    public ReportTest() {
        INITIAL_CREDITS.put(Currency.CAD, Credits.fromInteger(5000, Currency.CAD));
        FINAL_CREDITS.put(Currency.CAD, Credits.fromInteger(4959, Currency.CAD));
        FINAL_CREDITS_SOLD.put(Currency.CAD, Credits.fromString("4968.75", Currency.CAD));
    }

    @Mock
    private StockAPIRepository stockAPIRepository;


    @Before
    public void setup() {

    }

    @Test
    public void givenNoTransactions_whenMakingReport_reportHasInitialCreditsAndEmptyPortfolioValue() {
        List<TransactionBuy> transactionBuys = new ArrayList<>();
        List<TransactionSell> transactionSells = new ArrayList<>();
        List<Transaction> transactionList = new ArrayList<>();

        Report report = new Report(VALID_DATETIME, transactionList, INITIAL_CREDITS, transactionBuys, transactionSells, forexRepo, stockAPIRepository);

        assertEquals(INITIAL_CREDITS, report.getCredits());
        assertEquals(EMPTY_CREDITS, report.getPortfolioValue());
    }

    @Test
    public void givenTwoTransactionsAndNoSellTransaction_whenMakingReport_reportHasCorrectCreditsAndPortfolioValue() {
        when(this.stockAPIRepository.retrieveStockPrice(any(Stock.class), any(), eq(true))).thenReturn(STOCK_VALUE_CREDITS);
        List<TransactionBuy> transactionBuys = new ArrayList<>();
        Stock stock = new Stock(STOCK_NAME, STOCK_NAME);
        transactionBuys.add(new TransactionBuy(NUMBER_STOCK, VALID_DATETIME, stock, STOCK_VALUE_CREDITS, accountNumber));
        transactionBuys.add(new TransactionBuy(NUMBER_STOCK, VALID_DATETIME, stock, STOCK_VALUE_CREDITS, accountNumber));
        List<TransactionSell> transactionSells = new ArrayList<>();
        List<Transaction> transactionList = new ArrayList<>();

        Report report = new Report(VALID_DATETIME, transactionList, INITIAL_CREDITS, transactionBuys, transactionSells, forexRepo, stockAPIRepository);

        assertEquals(STOCK_VALUE_CREDITS_QUADRUPLE, report.getPortfolioValue());
        assertEquals(FINAL_CREDITS, report.getCredits());
    }

    @Test
    public void givenBuysAndSells_whenMakingReport_reportHasCorrectCreditsAndPortfolioValue() {
        when(this.stockAPIRepository.retrieveStockPrice(any(Stock.class), any(), eq(true))).thenReturn(STOCK_VALUE_CREDITS);
        List<TransactionBuy> transactionBuys = new ArrayList<>();
        Stock stock = new Stock(STOCK_NAME, STOCK_NAME);
        TransactionBuy t1 = new TransactionBuy(NUMBER_STOCK, VALID_DATETIME, stock, STOCK_VALUE_CREDITS, accountNumber);
        transactionBuys.add(t1);
        transactionBuys.add(new TransactionBuy(NUMBER_STOCK, VALID_DATETIME, stock, STOCK_VALUE_CREDITS, accountNumber));
        List<TransactionSell> transactionSells = new ArrayList<>();
        transactionSells.add(new TransactionSell(NUMBER_STOCK_SOLD, VALID_DATETIME, stock, STOCK_VALUE_CREDITS, t1.getTransactionID(), accountNumber));
        List<Transaction> transactionList = new ArrayList<>();

        Report report = new Report(VALID_DATETIME, transactionList, INITIAL_CREDITS, transactionBuys, transactionSells, forexRepo, stockAPIRepository);

        assertEquals(STOCK_VALUE_CREDITS_TRIPLE, report.getPortfolioValue());
        assertEquals(FINAL_CREDITS_SOLD, report.getCredits());
    }
}
