package trading.domain;

import trading.domain.transaction.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class ReportTest {

    private static final DateTime reportDateTime = new DateTime("2018-09-09", 23, 59, 59);
    private static final List<Transaction> emptyTransactionList = Collections.emptyList();

    @Mock
    private Account account;
    private Report report;

    @Test
    public void givenAccountAndDailyReport_whenAccountHasNoTransaction_thenReturnEmptyTransactionsList() {
        when(account.getDailyTransactions(reportDateTime)).thenReturn(emptyTransactionList);
        this.report = new Report(account, reportDateTime, ReportType.DAILY);
        List<Transaction> transactions = this.report.getReportTransactionsList();
        assertEquals(transactions, emptyTransactionList);
    }

}