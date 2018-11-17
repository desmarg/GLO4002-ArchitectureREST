package trading.domain.report;

import trading.domain.Credits;
import trading.domain.datetime.DateTime;
import trading.domain.transaction.Transaction;

import java.util.List;

public class Report {

    public final DateTime date;
    public final List<Transaction> transactions;
    public final Credits credits;
    public final Credits portfolioValue;

    public Report(DateTime date, List<Transaction> transactionList, Credits credits,
                  Credits portfolioValue) {
        this.date = date;
        this.transactions = transactionList;
        this.credits = credits;
        this.portfolioValue = portfolioValue;
    }
}
