//package trading.persistence;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.runners.MockitoJUnitRunner;
//import trading.domain.Account.Account;
//import trading.persistence.AccountNotFoundException;
//import trading.domain.Account.AccountNumber;
//import trading.domain.Credits.Credits;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNotEquals;
//import static org.junit.Assert.assertTrue;
//
//@RunWith(MockitoJUnitRunner.class)
//public class AccountRepositoryInMemoryTest {
//
//    private static final Long INVESTOR_ID = 1l;
//    private static final String INVESTOR_NAME = "Example Name";
//    private static final Credits CREDITS = Credits.fromDouble(1.1);
//    private static final Long NON_EXISTING_INVESTOR_ID = 456L;
//    private static final AccountNumber NON_EXISTING_ACCOUNT_NUMBER = new AccountNumber("TA-456");
//    private static final AccountNumber EXISTING_ACCOUNT_NUMBER = new AccountNumber("AL-420");
//
//    private Account account;
//    private AccountRepositoryInMemory accountRepositoryInMemory;
//
//    @Before
//    public void setUp() {
//        this.accountRepositoryInMemory = new AccountRepositoryInMemory();
//        this.account = new Account(
//                INVESTOR_ID,
//                INVESTOR_NAME,
//                CREDITS,
//                EXISTING_ACCOUNT_NUMBER
//        );
//    }
//
//    @Test(expected = AccountNotFoundException.class)
//    public void givenNonexistentAccount_whenFindingAccount_thenThrowAccountNotFoundException() {
//        this.accountRepositoryInMemory.findByAccountNumber(NON_EXISTING_ACCOUNT_NUMBER);
//    }
//
//    @Test
//    public void whenSave_thenRightAccountIsSaved() {
//        Account savedAccount = this.accountRepositoryInMemory.save(this.account);
//
//        Account inMemoryAccount = this.accountRepositoryInMemory.findByAccountNumber(
//                savedAccount.getString()
//        );
//
//        assertEquals(savedAccount, inMemoryAccount);
//    }
//
//    @Test
//    public void whenSave_thenCounterIsDifferentThanInitial() {
//        Long initialCounter = this.accountRepositoryInMemory.getCurrentAccountId();
//        this.accountRepositoryInMemory.save(this.account);
//
//        assertNotEquals(initialCounter, this.accountRepositoryInMemory.getCurrentAccountId());
//    }
//
//    @Test
//    public void givenAccountNotInRepository_whenCheckingIfAccountExists_thenReturnsFalse() {
//        assertFalse(this.accountRepositoryInMemory.validateAccountDoesNotExists(NON_EXISTING_INVESTOR_ID));
//    }
//
//    @Test
//    public void givenAccountInRepository_whenCheckingIfAccountExists_thenReturnsTrue() {
//        this.accountRepositoryInMemory.save(this.account);
//
//        assertTrue(this.accountRepositoryInMemory.validateAccountDoesNotExists(INVESTOR_ID));
//    }
//
//}
