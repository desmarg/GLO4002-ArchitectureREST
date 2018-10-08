package trading.domain;

import trading.domain.transaction.Transaction;
import trading.exception.UnsupportedReportTypeException;

import java.util.List;

public class Report {

    private Account account;
    private DateTime date;
    private ReportType type;

    public Report(Account account, DateTime date, ReportType type) {
        this.account = account;
        this.date = date;
        this.type = type;
    }

    public List<Transaction> getReportTransactionsList(){
        if(this.type == ReportType.DAILY){
            return account.getDailyTransactions(this.date);
        }
        throw new UnsupportedReportTypeException(this.type.toString());
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public ReportType getType() {
        return type;
    }

    public void setType(ReportType type) {
        this.type = type;
    }
}
