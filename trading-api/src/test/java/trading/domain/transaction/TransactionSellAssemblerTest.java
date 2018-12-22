package trading.domain.transaction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import trading.api.request.StockDTO;
import trading.api.request.TransactionPostRequestDTO;
import trading.domain.Credits;
import trading.domain.Currency;
import trading.domain.account.AccountNumber;

@RunWith(MockitoJUnitRunner.class)
public class TransactionSellAssemblerTest {
    private static final Long INVALID_QUANTITY = 0L;
    private static final StockDTO VALID_STOCK_DTO = new StockDTO();
    private static final Credits CREDITS = Credits.fromInteger(0, Currency.XXX);
    private final AccountNumber AN_ACCOUNT_NUMBER = new AccountNumber("TD-0000");

    @Test(expected = InvalidQuantityException.class)
    public void givenInvalidQuantity_whenFromDTO_thenThrowInvalidQuantityException() {
        TransactionPostRequestDTO transactionPostRequestDTO = new TransactionPostRequestDTO();
        transactionPostRequestDTO.quantity = INVALID_QUANTITY;
        transactionPostRequestDTO.stock = VALID_STOCK_DTO;
        TransactionSellAssembler.fromDTO(transactionPostRequestDTO, this.AN_ACCOUNT_NUMBER,
                CREDITS);
    }
}