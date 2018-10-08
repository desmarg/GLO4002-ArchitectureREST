package trading.domain.transaction;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class TransactionNumberTest {

    private static final TransactionNumber A_TRANSACTION_NUMBER = new TransactionNumber();
    private static final TransactionNumber ANOTHER_TRANSACTION_NUMBER = new TransactionNumber();

    @Test
    public void givenTwoDifferentTransactionNumber_whenCheckingIfEquals_thenReturnsFalse() {
        assertFalse(A_TRANSACTION_NUMBER.equals(ANOTHER_TRANSACTION_NUMBER));
    }

    @Test
    public void givenTwoTransactionsWithEqualTransactionNumbers_whenCheckingIfEquals_thenReturnsTrue() {
        UUID firstTransactionNumber =  A_TRANSACTION_NUMBER.getId();
        TransactionNumber secondTransaction = new TransactionNumber(firstTransactionNumber);

        assertTrue(A_TRANSACTION_NUMBER.equals(secondTransaction));
    }

}