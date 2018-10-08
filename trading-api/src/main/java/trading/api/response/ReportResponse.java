package trading.api.response;

import trading.domain.Report;
import trading.domain.transaction.Transaction;
import trading.factory.TransactionResponseFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ReportResponse {
    private String date;
    private List<TransactionResponse> transactions;

    public ReportResponse() {
    }

    public ReportResponse(Report report) {
        this.date = report.getDate().toString();
        this.transactions = report.getReportTransactionsList().stream().map(transaction -> {
            return TransactionResponseFactory.createTransactionDto(transaction);
        }).collect(Collectors.toList());
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<TransactionResponse> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions.stream().map(transaction -> {
            return TransactionResponseFactory.createTransactionDto(transaction);
        }).collect(Collectors.toList());
    }

}
