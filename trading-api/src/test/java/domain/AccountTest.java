package domain;

import api.account.InvalidCreditsAmountException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class AccountTest {


    private static final Long AN_INVESTOR_ID = 123L;
    private static final Long ANOTHER_INVESTOR_ID = 456L;
    private static final String AN_INVESTOR_NAME = "Bob";
    private static final String AN_INVESTOR_EMAIL = "bob@bob.com";
    private static final BigDecimal A_CREDITS_AMOUNT = new BigDecimal(420.69);
    private static final BigDecimal INVALID_CREDITS_AMOUNT = new BigDecimal(0.00);

    @Test
    public void givenExistingCreatedAccount_whenCreatingANewAccount_thenItsAccountNumberIsOneMoreThanTheLatestCreated() {
        Account firstAccount = new Account(AN_INVESTOR_ID, AN_INVESTOR_NAME, AN_INVESTOR_EMAIL, A_CREDITS_AMOUNT);
        long firstAccountNumber = firstAccount.getAccountNumber();

        Account nextAccount = new Account(ANOTHER_INVESTOR_ID, AN_INVESTOR_NAME, AN_INVESTOR_EMAIL, A_CREDITS_AMOUNT);
        long nextAccountNumber = nextAccount.getAccountNumber();

        assertEquals(firstAccountNumber + 1, nextAccountNumber);
    }

    @Test
    public void whenCreatingANewAccount_thenItsProfileIsAlsoCreated() {
        Account newAccount = new Account(AN_INVESTOR_ID, AN_INVESTOR_NAME, AN_INVESTOR_EMAIL, A_CREDITS_AMOUNT);

        assertNotNull(newAccount.getInvestorProfile());
    }

    @Test(expected = InvalidCreditsAmountException.class)
    public void whenCreatingAccountWithInvalidCreditsAmount_thenThrowInvalidCreditsAmountException(){
        Account newAccount = new Account(AN_INVESTOR_ID, AN_INVESTOR_NAME, AN_INVESTOR_EMAIL, INVALID_CREDITS_AMOUNT);
    }
}