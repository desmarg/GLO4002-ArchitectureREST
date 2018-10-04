//package domain.account;
//
//import org.junit.runner.RunWith;
//import org.mockito.runners.MockitoJUnitRunner;
//
//@RunWith(MockitoJUnitRunner.class)
//public class AccountNumberTest {
//    private static long ACCOUNT_NUMBER = 1L;
//    private static long DIFFERENT_ACCOUNT_NUMBER = 2L;
//
//    @Test
//    public void givenAccountNumber_whenEqualsAValidAccountNumber_thenReturnTrue() {
//        AccountNumber accountNumber = new AccountNumber(ACCOUNT_NUMBER);
//        AccountNumber similarAccountNumber = new AccountNumber(ACCOUNT_NUMBER);
//
//        assertEquals(true, accountNumber.equals(similarAccountNumber));
//    }
//
//    @Test
//    public void givenAccountNumber_whenEqualsADifferentAccountNumber_thenReturnFalse() {
//        AccountNumber accountNumber = new AccountNumber(ACCOUNT_NUMBER);
//        AccountNumber differentAccountNumber = new AccountNumber(DIFFERENT_ACCOUNT_NUMBER);
//        assertEquals(false, accountNumber.equals(differentAccountNumber));
//    }
//}