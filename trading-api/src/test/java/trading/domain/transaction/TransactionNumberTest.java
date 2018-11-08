package trading.domain.transaction;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TransactionNumberTest {

    private static final TransactionNumber A_TRANSACTION_NUMBER = new TransactionNumber();
    private static final TransactionNumber ANOTHER_TRANSACTION_NUMBER = new TransactionNumber();

    @Test
    public void givenTwoDifferentTransactionNumber_whenCheckingIfEquals_thenReturnsFalse() {
        assertFalse(A_TRANSACTION_NUMBER.equals(ANOTHER_TRANSACTION_NUMBER));
    }

    @Test
    public void
    givenTwoTransactionsWithEqualTransactionNumbers_whenCheckingIfEquals_thenReturnsTrue() {
        UUID firstTransactionNumberAsUuid = A_TRANSACTION_NUMBER.getId();
        TransactionNumber secondTransaction = new TransactionNumber(firstTransactionNumberAsUuid);

        assertTrue(A_TRANSACTION_NUMBER.equals(secondTransaction));
    }

    @Test
    public void givenObjectThatIsNotOfTypeTransactionNumber_whenCheckingIfEquals_thenReturnsFalse
            () {
        UUID transactionNumberAsUuid = A_TRANSACTION_NUMBER.getId();

        assertFalse(A_TRANSACTION_NUMBER.equals(transactionNumberAsUuid));
    }
}