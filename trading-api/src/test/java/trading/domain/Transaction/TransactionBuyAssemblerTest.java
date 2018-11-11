package trading.domain.Transaction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import trading.api.request.StockDTO;
import trading.api.request.TransactionPostRequestDTO;
import trading.domain.Account.AccountNumber;
import trading.domain.DateTime.DateTime;
import trading.services.StockService;

import java.time.Instant;

@RunWith(MockitoJUnitRunner.class)
public class TransactionBuyAssemblerTest {

    public static final long INVALID_QUANTITY = 0L;
    private final AccountNumber accountNumber = new AccountNumber("TD-0000");
    private static final Instant INSTANT = new DateTime("2015-01-01T05:00:00.000Z").toInstant();
    private static final StockDTO VALID_STOCKDTO = new StockDTO();
    private static final String MARKET = "bla";
    private static final String SYMBOL = "bla";


    @Mock
    StockService stockService;

    @Test(expected = InvalidQuantityException.class)
    public void givenBuyQuantitySmallerThanOne_whenMakingTransaction_thenThrowInvalidQuantityException() {
        TransactionPostRequestDTO transactionPostRequestDTO = new TransactionPostRequestDTO();
        transactionPostRequestDTO.date = INSTANT;
        transactionPostRequestDTO.quantity = INVALID_QUANTITY;
        transactionPostRequestDTO.stock = VALID_STOCKDTO;
        transactionPostRequestDTO.stock.market = MARKET;
        transactionPostRequestDTO.stock.symbol = SYMBOL;
        TransactionBuyAssembler.fromDTO(transactionPostRequestDTO, this.accountNumber, this.stockService);
    }
}