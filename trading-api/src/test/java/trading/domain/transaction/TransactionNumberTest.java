package trading.domain.transaction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(MockitoJUnitRunner.class)
public class TransactionNumberTest {

    private static final TransactionNumber A_TRANSACTION_NUMBER = new TransactionNumber();
    private static final TransactionNumber ANOTHER_TRANSACTION_NUMBER = new TransactionNumber();

    @Test
    public void givenTwoDifferentTransactionNumber_whenCheckingIfEquals_thenReturnsFalse() {
        assertNotEquals(A_TRANSACTION_NUMBER, ANOTHER_TRANSACTION_NUMBER);
    }

    @Test
    public void
    givenTwoTransactionsWithEqualTransactionNumbers_whenCheckingIfEquals_thenReturnsTrue() {
        UUID firstTransactionNumberAsUuid = A_TRANSACTION_NUMBER.getId();
        TransactionNumber secondTransaction = new TransactionNumber(firstTransactionNumberAsUuid);

        assertEquals(A_TRANSACTION_NUMBER, secondTransaction);
    }

    @Test
    public void givenObjectThatIsNotOfTypeTransactionNumber_whenCheckingIfEquals_thenReturnsFalse
            () {
        UUID transactionNumberAsUuid = A_TRANSACTION_NUMBER.getId();

        assertNotEquals(A_TRANSACTION_NUMBER, transactionNumberAsUuid);
    }
}