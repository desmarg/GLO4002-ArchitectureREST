package trading.domain.report;

import trading.domain.Credits;
import trading.domain.Currency;
import trading.domain.datetime.DateTime;
import trading.domain.transaction.Transaction;

import java.util.HashMap;
import java.util.List;

public class Report {

    public final DateTime date;
    public final List<Transaction> transactions;
    public final HashMap<Currency, Credits> credits;
    public final Credits portfolioValue;

    public Report(DateTime date, List<Transaction> transactionList, HashMap<Currency, Credits> credits,
                  Credits portfolioValue) {
        this.date = date;
        this.transactions = transactionList;
        this.credits = credits;
        this.portfolioValue = portfolioValue;
    }
}
