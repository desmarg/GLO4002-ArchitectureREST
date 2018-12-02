package trading.api.response;

import trading.domain.transaction.Transaction;

import java.math.BigDecimal;

public class TransactionSellResponseDTO extends TransactionResponseDTO {
    public final BigDecimal priceSold;

    public TransactionSellResponseDTO(Transaction transaction) {
        super(transaction);
        this.priceSold = transaction.getStockPrice().getAmount();
    }
}
