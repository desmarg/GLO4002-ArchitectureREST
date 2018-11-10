package trading.domain.transaction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import trading.api.request.TransactionPostRequestDTO;
import trading.domain.Account.AccountNumber;
import trading.services.StockService;

@RunWith(MockitoJUnitRunner.class)
public class TransactionBuyAssemblerTest {

    public static final long INVALID_QUANTITY = 0L;
    private final AccountNumber accountNumber = new AccountNumber("TD-0000");
    
    @Mock
    StockService stockService;

    @Test(expected = InvalidQuantityException.class)
    public void givenBuyQuantitySmallerThanOne_whenMakingTransaction_thenThrowInvalidQuantityException() {
        TransactionPostRequestDTO transactionPostRequestDTO = new TransactionPostRequestDTO();
        transactionPostRequestDTO.quantity = INVALID_QUANTITY;
        TransactionBuyAssembler.fromDTO(transactionPostRequestDTO, this.accountNumber, this.stockService);
    }
}