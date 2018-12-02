package trading.api.response;

import trading.domain.report.Report;
import trading.domain.transaction.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ReportResponseDTO {
    public final String date;
    public final List<TransactionResponseDTO> transactions;
    public final BigDecimal credits;
    public final BigDecimal portfolioValue;


    public ReportResponseDTO(Report report) {
        this.date = report.date.toInstant().toString();

        this.transactions = new ArrayList<>();
        for (Transaction transaction : report.transactions) {
            this.transactions.add(TransactionResponseDTOFactory
                    .createTransactionResponse(transaction)
            );
        }
        this.credits = report.credits.getAmount();
        this.portfolioValue = report.portfolioValue.getAmount();
    }
}
