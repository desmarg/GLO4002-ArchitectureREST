package trading.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import trading.domain.Credits;
import trading.domain.Stock;
import trading.domain.account.AccountNumber;
import trading.domain.datetime.DateTime;
import trading.domain.report.Portfolio;
import trading.domain.transaction.TransactionBuy;
import trading.domain.transaction.TransactionSell;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReportServiceTest {
    private final DateTime VALID_DATETIME = new DateTime(Instant.parse("2000-08-04T05:00:00Z"));
    private static final Credits INITIAL_CREDITS = Credits.fromInteger(5000);
    private static final Credits FINAL_CREDITS = Credits.fromInteger(4959);
    private static final Credits FINAL_CREDITS_SOLD = Credits.fromString("4968.75");
    private static final Credits STOCK_VALUE_CREDITS = Credits.fromInteger(10);
    private static final Credits STOCK_VALUE_CREDITS_TRIPLE = Credits.fromInteger(30);
    private static final Credits STOCK_VALUE_CREDITS_QUADRUPLE = Credits.fromInteger(40);
    private static final Credits EMPTY_CREDITS = Credits.fromInteger(0);
    private static final long NUMBER_STOCK = 2L;
    private static final long NUMBER_STOCK_SOLD = 1L;
    private static final String STOCK_NAME = "ABC";
    private static final AccountNumber accountNumber = new AccountNumber("TD-0001");

    private ReportService reportService;
    @Mock
    private StockService stockService;

    @Before
    public void setup() {
        reportService = new ReportService(stockService);
    }

    @Test
    public void givenNoTransactions_whenCalculatingPortfolio_returnEmptyPortfolio() {
        List<TransactionBuy> transactionBuys = new ArrayList<>();
        List<TransactionSell> transactionSells = new ArrayList<>();

        Portfolio portfolio = reportService.getPortfolio(INITIAL_CREDITS, VALID_DATETIME, transactionBuys, transactionSells);
        assertEquals(INITIAL_CREDITS, portfolio.accountValue);
        assertEquals(EMPTY_CREDITS, portfolio.portfolioValue);
    }

    @Test
    public void givenBuysAndNoSells_whenCalculatingPortfolio_returnPortfolioWithGoodValue() {
        when(this.stockService.retrieveStockPrice(any(Stock.class), any())).thenReturn(STOCK_VALUE_CREDITS);

        List<TransactionBuy> transactionBuys = new ArrayList<>();
        Stock stock = new Stock(STOCK_NAME, STOCK_NAME);
        transactionBuys.add(new TransactionBuy(NUMBER_STOCK, VALID_DATETIME, stock, STOCK_VALUE_CREDITS, accountNumber));
        transactionBuys.add(new TransactionBuy(NUMBER_STOCK, VALID_DATETIME, stock, STOCK_VALUE_CREDITS, accountNumber));
        List<TransactionSell> transactionSells = new ArrayList<>();

        Portfolio portfolio = reportService.getPortfolio(INITIAL_CREDITS, VALID_DATETIME, transactionBuys, transactionSells);
        System.out.println(portfolio);
        assertEquals(STOCK_VALUE_CREDITS_QUADRUPLE, portfolio.portfolioValue);
        assertEquals(FINAL_CREDITS, portfolio.accountValue);
    }

    @Test
    public void givenBuysAndSells_whenCalculating_returnPortfolioWithGoodValue() {
        when(this.stockService.retrieveStockPrice(any(Stock.class), any())).thenReturn(STOCK_VALUE_CREDITS);

        List<TransactionBuy> transactionBuys = new ArrayList<>();
        Stock stock = new Stock(STOCK_NAME, STOCK_NAME);
        TransactionBuy t1 = new TransactionBuy(NUMBER_STOCK, VALID_DATETIME, stock, STOCK_VALUE_CREDITS, accountNumber);
        transactionBuys.add(t1);
        transactionBuys.add(new TransactionBuy(NUMBER_STOCK, VALID_DATETIME, stock, STOCK_VALUE_CREDITS, accountNumber));
        List<TransactionSell> transactionSells = new ArrayList<>();
        transactionSells.add(new TransactionSell(NUMBER_STOCK_SOLD, VALID_DATETIME, stock, STOCK_VALUE_CREDITS, t1.getTransactionNumber(), accountNumber));

        Portfolio portfolio = reportService.getPortfolio(INITIAL_CREDITS, VALID_DATETIME, transactionBuys, transactionSells);
        System.out.println(portfolio);
        assertEquals(STOCK_VALUE_CREDITS_TRIPLE, portfolio.portfolioValue);
        assertEquals(FINAL_CREDITS_SOLD, portfolio.accountValue);
    }
}
