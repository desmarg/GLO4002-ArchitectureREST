package trading.domain.transaction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionIDTest {

    private static final TransactionID A_TRANSACTION_NUMBER = new TransactionID();
    private static final TransactionID ANOTHER_TRANSACTION_NUMBER = new TransactionID();

    @Test
    public void givenTwoDifferentTransactionNumber_whenCheckingIfEquals_thenReturnsFalse() {
        assertNotEquals(A_TRANSACTION_NUMBER, ANOTHER_TRANSACTION_NUMBER);
    }

    @Test
    public void givenTwoTransactionsWithEqualTransactionNumbers_whenCheckingIfEquals_thenReturnsTrue() {
        UUID firstTransactionNumberAsUuid = A_TRANSACTION_NUMBER.getId();
        TransactionID secondTransaction = new TransactionID(firstTransactionNumberAsUuid);

        assertEquals(A_TRANSACTION_NUMBER, secondTransaction);
    }

    @Test
    public void givenObjectThatIsNotOfTypeTransactionNumber_whenCheckingIfEquals_thenReturnsFalse() {
        UUID transactionNumberAsUuid = A_TRANSACTION_NUMBER.getId();

        assertNotEquals(A_TRANSACTION_NUMBER, transactionNumberAsUuid);
    }
}