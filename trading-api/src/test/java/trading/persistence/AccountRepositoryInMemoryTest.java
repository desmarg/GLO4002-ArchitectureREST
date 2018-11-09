package trading.persistence;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import trading.domain.Account.Account;
import trading.domain.Account.AccountNumber;
import trading.domain.Credits;
import trading.exception.AccountNotFoundException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountRepositoryInMemoryTest {

    private static final Long INVESTOR_ID = 1l;
    private static final String INVESTOR_NAME = "Example Name";
    private static final String EMAIL = "example@mail.com";
    private static final Credits CREDITS = Credits.fromDouble(1.1);
    private static final Long NON_EXISTING_INVESTOR_ID = 456L;
    private static final AccountNumber NON_EXISTING_ACCOUNT_NUMBER = new AccountNumber("TA-456");

    private Account account;
    private AccountRepositoryInMemory accountRepositoryInMemory;

    @Before
    public void setUp() {
        this.accountRepositoryInMemory = new AccountRepositoryInMemory();
        this.account = new Account(
                INVESTOR_ID,
                INVESTOR_NAME,
                EMAIL,
                CREDITS
        );
    }

    @Test(expected = AccountNotFoundException.class)
    public void givenNonexistentAccount_whenFindingAccount_thenThrowAccountNotFoundException() {
        this.accountRepositoryInMemory.findByAccountNumber(NON_EXISTING_ACCOUNT_NUMBER);
    }

    @Test
    public void whenSave_thenPersists() {
        Account savedAccount = this.accountRepositoryInMemory.save(this.account);

        this.accountRepositoryInMemory.findByAccountNumber(
                savedAccount.getAccountNumber()
        );
    }

    @Test
    public void whenSave_thenCounterIncrements() {
        Long initialCounter = this.accountRepositoryInMemory.getCounter();
        this.accountRepositoryInMemory.save(this.account);

        assertEquals(++initialCounter, this.accountRepositoryInMemory.getCounter());
    }

    @Test
    public void givenAccountNotInRepository_whenCheckingIfAccountExists_thenReturnsFalse() {
        this.accountRepositoryInMemory.save(this.account);

        assertFalse(this.accountRepositoryInMemory.accountAlreadyExists(NON_EXISTING_INVESTOR_ID));
    }

    @Test
    public void givenAccountInRepository_whenCheckingIfAccountExists_thenReturnsTrue() {
        this.accountRepositoryInMemory.save(this.account);

        assertTrue(this.accountRepositoryInMemory.accountAlreadyExists(INVESTOR_ID));
    }

}
