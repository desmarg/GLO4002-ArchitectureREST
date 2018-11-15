package trading.api.response;

import trading.domain.Report.Report;
import trading.domain.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ReportResponse {

    public final String date;
    public final List<TransactionResponse> transactions;
    public final Float credits;
    public final Float portfolioValue;


    public ReportResponse(Report report) {
        this.date = report.date.toInstant().toString();

        this.transactions = new ArrayList<>();
        for (Transaction transaction : report.transactions) {
            this.transactions.add(TransactionResponseFactory.createTransactionResponse(transaction));
        }
        this.credits = report.credits.valueToFloat();
        this.portfolioValue = report.portfolioValue.valueToFloat();
    }
}
