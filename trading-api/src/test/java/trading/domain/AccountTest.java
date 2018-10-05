//package trading.domain;
//
//import exception.InvalidCreditsAmountException;
//import trading.domain.investorprofile.InvestorProfile;
//import trading.domain.investorprofile.ProfileType;
//import org.junit.Test;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//public class AccountTest {
//
//    private static final Long AN_INVESTOR_ID = 123L;
//    private static final String AN_INVESTOR_NAME = "Bob";
//    private static final String AN_INVESTOR_EMAIL = "bob@bob.com";
//    private static final BigDecimal A_CREDITS_AMOUNT = new BigDecimal(420.69);
//    private static final BigDecimal INVALID_CREDITS_AMOUNT = new BigDecimal(0.00);
//    private static final Long A_VALID_ACCOUNT_NUMBER = 0L;
//    private static final List<String> EMPTY_FOCUS_AREAS = new ArrayList<String>();
//    private static final InvestorProfile AN_INVESTOR_PROFILE = new InvestorProfile(ProfileType.CONSERVATIVE, new ArrayList<String>());
//
//    @Test
//    public void whenCreatingANewAccount_thenItsProfileIsAlsoCreated() {
//        Account newAccount = new Account(
//                AN_INVESTOR_ID,
//                AN_INVESTOR_NAME,
//                AN_INVESTOR_EMAIL,
//                A_CREDITS_AMOUNT,
//                A_VALID_ACCOUNT_NUMBER,
//                AN_INVESTOR_PROFILE
//        );
//        assertNotNull(newAccount.getInvestorProfile());
//    }
//
//    @Test(expected = InvalidCreditsAmountException.class)
//    public void
//    whenCreatingAccountWithInvalidCreditsAmount_thenThrowInvalidCreditsAmountException() {
//        Account newAccount = new Account(
//                AN_INVESTOR_ID,
//                AN_INVESTOR_NAME,
//                AN_INVESTOR_EMAIL,
//                INVALID_CREDITS_AMOUNT,
//                A_VALID_ACCOUNT_NUMBER,
//                AN_INVESTOR_PROFILE
//        );
//    }
//}
