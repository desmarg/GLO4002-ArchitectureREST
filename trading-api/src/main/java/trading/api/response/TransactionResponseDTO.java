package trading.api.response;

import trading.domain.Stock;
import trading.domain.transaction.Transaction;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public abstract class TransactionResponseDTO {
    public final String type;
    public final Instant date;
    public final Stock stock;
    public final UUID transactionNumber;
    public final Long quantity;
    public final BigDecimal fees;

    public TransactionResponseDTO(Transaction transaction) {
        this.type = transaction.getTransactionType().toString();
        this.date = transaction.getDateTime().toInstant();
        this.stock = transaction.getStock();
        this.transactionNumber = transaction.getTransactionNumber().getId();
        this.quantity = transaction.getQuantity();
        this.fees = transaction.getFees().toBigDecimal();
    }
}