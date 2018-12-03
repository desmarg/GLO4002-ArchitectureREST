package trading.api.response;

import trading.domain.Credits;
import trading.domain.Currency;
import trading.domain.report.Report;
import trading.domain.transaction.Transaction;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;

public class ReportResponseAssembler {
    public static ReportResponseDTO toReportResponseDTO(Report report) {
        ReportResponseDTO reportResponseDTO = new ReportResponseDTO();
        reportResponseDTO.date = report.date.toInstant().toString();

        reportResponseDTO.transactions = new ArrayList<>();
        for (Transaction transaction : report.transactions) {
            reportResponseDTO.transactions.add(TransactionResponseDTOFactory
                    .createTransactionResponse(transaction)
            );
        }
        reportResponseDTO.credits = createCreditListFromMap(report.credits);
        reportResponseDTO.portfolioValue = report.portfolioValue.getAmount().setScale(2, RoundingMode.HALF_UP);

        return reportResponseDTO;
    }

    private static ArrayList<Credits> createCreditListFromMap(HashMap<Currency, Credits> creditMap) {
        return new ArrayList<>(creditMap.values());
    }
}
