package trading.api.response;

import trading.domain.Credits;
import trading.domain.report.Report;
import trading.domain.transaction.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ReportResponseDTO {
    public String date;
    public List<TransactionResponseDTO> transactions;
    public ArrayList<Credits> credits;
    public BigDecimal portfolioValue;


    public ReportResponseDTO() {
    }
}
