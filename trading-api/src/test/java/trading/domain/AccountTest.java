package trading.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import trading.domain.transaction.Transaction;
import trading.domain.transaction.TransactionNumber;
import trading.exception.InvalidAccountInfoException;
import trading.exception.InvalidCreditsAmountException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {

    private static final Long VALID_INVESTOR_ID = 1l;
    private static final String VALID_INVESTOR_NAME = "FirstName LastName";
    private static final String VALID_EMAIL = "first.last@example.com";
    private static final AccountNumber ACCOUNT_NUMBER = new AccountNumber("FL-0001");
    private static final Credits INVALID_INITIAL_CREDITS = Credits.fromDouble(-10);
    private static final Credits A_CREDIT_ACCOUNT = Credits.fromDouble(10);
    private static final TransactionNumber VALID_TRANSACTIONNUMBER = new TransactionNumber();

    private static final String INVALID_EMAIL = "first.last.com";
    private static final String INVALID_INVESTOR_NAME = "12345";

    @Mock
    private Credits creditsMock;

    private Transaction transaction;

    private Account basicAccount;


    @Before
    public void setup() {
        when(this.creditsMock.compareTo(any(Credits.class))).thenReturn(1);
        this.basicAccount = new Account(VALID_INVESTOR_ID, VALID_INVESTOR_NAME, VALID_EMAIL, this.creditsMock);
        this.basicAccount.setAccountNumber(ACCOUNT_NUMBER);
    }

    @Test
    public void whenCreatingNewAccount_thenProfileTypeIsConservative() {
        assertEquals(ProfileType.CONSERVATIVE, this.basicAccount.getInvestorProfile().getProfileType());
    }

    @Test(expected = InvalidAccountInfoException.class)
    public void givenInvalidEmail_whenValidating_thenThrowInvalidAccountInfoException() {
        this.basicAccount.validateEmail(INVALID_EMAIL);
    }

    @Test(expected = InvalidAccountInfoException.class)
    public void givenInvalidInvestorName_whenValidating_thenThrowInvalidAccountInfoException() {
        this.basicAccount.validateInvestorName(INVALID_INVESTOR_NAME);
    }


    @Test(expected = InvalidCreditsAmountException.class)
    public void givenInvalidCreditsAmount_whenValidating_thenThrowInvalidCreditsAmountException() {
        this.basicAccount.validateInitialCredits(INVALID_INITIAL_CREDITS);
    }

    @Test
    public void givenAccountWithEnoughCredits_whenSubtractingCredits_thenSubtractIsCalledOnCreditsWithGoodAmount() {
        this.basicAccount.subtractCredits(A_CREDIT_ACCOUNT);
        verify(this.creditsMock).subtract(A_CREDIT_ACCOUNT);
    }

    @Test
    public void whenAddingCreditsToAccount_thenAddIsCalledOnCreditsWithGoodAmount() {
        this.basicAccount.addCredits(A_CREDIT_ACCOUNT);
        verify(this.creditsMock).add(A_CREDIT_ACCOUNT);
    }


    @Test
    public void givenSufficientFunds_whenCheckingBalance_thenReturnTrue() {
        when(this.creditsMock.compareTo(any(Credits.class))).thenReturn(0);
        assertTrue(this.basicAccount.hasEnoughCreditsToPay(this.creditsMock));
    }

    @Test
    public void givenInsufficientFunds_whenCheckingBalance_thenReturnFalse() {
        when(this.creditsMock.compareTo(any(Credits.class))).thenReturn(-1);
        assertFalse(this.basicAccount.hasEnoughCreditsToPay(this.creditsMock));
    }

}
