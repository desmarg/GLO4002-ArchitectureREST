package trading.api.response;

import trading.domain.transaction.Transaction;

import java.math.BigDecimal;

public class TransactionBuyResponseDTO extends TransactionResponseDTO {
    public BigDecimal purchasedPrice;

    public TransactionBuyResponseDTO(Transaction transaction) {
        super(transaction);
        this.purchasedPrice = transaction.getStockPrice().toBigDecimal();
    }
}
