//package trading.persistence;
//
//import org.junit.runner.RunWith;
//import org.mockito.runners.MockitoJUnitRunner;
//
//@RunWith(MockitoJUnitRunner.class)
//public class AccountRepositoryInMemoryTest {
//
//    private static final AccountNumber AN_ACCOUNT_NUMBER = new AccountNumber(123L);
//    private static final AccountNumber NON_EXISTING_ACCOUNT_NUMBER = new AccountNumber(456L);
//
//    private static final long AN_INVESTOR_ID = 456L;
//    private static final long NON_EXISTING_INVESTOR_ID= 456L;
//
//    @Mock
//    private Account account;
//
//    private AccountRepositoryInMemory accountRepositoryInMemory;
//
//    @Before
//    public void setUp() {
//        accountRepositoryInMemory = new AccountRepositoryInMemory();
//        BDDMockito.willReturn(AN_ACCOUNT_NUMBER).given(account).getAccountNumber();
//    }
//
//    @Test
//    public void whenAccountIsNotInRepository_thenAccountExistsReturnFalse() {
//        assertFalse(accountRepositoryInMemory.checkIfAccountExists(NON_EXISTING_INVESTOR_ID));
//    }
//
//    @Test(expected = AccountNotFoundException.class)
//    public void givenNonexistentAccount_whenFindingAccount_thenThrowAccountNotFoundException() {
//        accountRepositoryInMemory.findByAccountNumber(NON_EXISTING_ACCOUNT_NUMBER);
//    }
//}
