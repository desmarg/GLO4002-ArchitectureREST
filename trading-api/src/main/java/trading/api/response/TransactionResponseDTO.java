package trading.api.response;

import trading.domain.DateTime.DateTime;
import trading.domain.Stock;
import trading.domain.transaction.Transaction;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public abstract class TransactionResponseDTO {
    public String type;
    public Instant date;
    public Stock stock;
    public UUID transactionNumber;
    public Long quantity;
    public BigDecimal fees;

    public TransactionResponseDTO(Transaction transaction) {
        this.type = transaction.getTransactionType().toString();
        this.date = transaction.getDateTime().toInstant();
        this.stock = transaction.getStock();
        this.transactionNumber = transaction.getTransactionNumber().getId();
        this.quantity = transaction.getQuantity();
        this.fees = transaction.getFees().toBigDecimal();
    }
}
