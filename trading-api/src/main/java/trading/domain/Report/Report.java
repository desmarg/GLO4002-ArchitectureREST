package trading.domain.Report;

import trading.domain.DateTime.DateTime;
import trading.domain.transaction.Transaction;

import java.util.List;

public class Report {

    public final DateTime date;
    public final List<Transaction> transactions;

    public Report(DateTime date, List<Transaction> transactionList) {
        this.date = date;
        this.transactions = transactionList;
    }
}
