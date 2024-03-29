package trading.api.response;

import trading.domain.Stock;
import trading.domain.transaction.Transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

public abstract class TransactionResponseDTO {
    public final String type;
    public final String date;
    public final Stock stock;
    public final UUID transactionNumber;
    public final Long quantity;
    public final BigDecimal fees;

    public TransactionResponseDTO(Transaction transaction) {
        this.type = transaction.getTransactionType().toString();
        this.date = transaction.getDateTime().toInstant().toString();
        this.stock = transaction.getStock();
        this.transactionNumber = transaction.getTransactionNumber().getId();
        this.quantity = transaction.getQuantity();
        this.fees = transaction.getFees().getAmount().setScale(2, RoundingMode.HALF_UP);
    }
}
