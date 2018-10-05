package trading.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreditsTest {

    private static final String ZERO_REPRESENTATION = "0.00";
    private static final String POINT_ONE_REPRESENTATION = "0.10";
    private static final double POINT_ONE = 0.1;
    private static final double POINT_ZERO_FIVE = 0.05;
    private static final long DOUBLE_QUANTITY = 2;


    @Test
    public void givenNewlyCreatedCredits_WhenDefaultConstructor_ThenValueIsZero() {
        Credits defaultCredits = new Credits();
        assertEquals(ZERO_REPRESENTATION, defaultCredits.valueToString());
    }

    @Test
    public void givenNewlyCreatedCredits_WhenConstructingFromFloat_ThenValueHasTwoDecimals() {
        Credits credits = Credits.fromDouble(POINT_ONE);
        assertEquals(POINT_ONE_REPRESENTATION, credits.valueToString());
    }

    @Test
    public void givenNonZeroCredits_WhenMultiplyingWithQuantity_ThenMultiplyValue() {
        Credits credits = Credits.fromDouble(POINT_ZERO_FIVE);
        credits.multiply(DOUBLE_QUANTITY);
        assertEquals(POINT_ONE_REPRESENTATION, credits.valueToString());
    }


    @Test
    public void givenCredits_WhenAddingNonZeroCredits_ThenAddValue() {
        Credits credits = Credits.fromDouble(POINT_ZERO_FIVE);
        Credits creditsToAdd = Credits.fromDouble(POINT_ZERO_FIVE);
        credits.add(creditsToAdd);
        assertEquals(POINT_ONE_REPRESENTATION, credits.valueToString());
    }

    @Test
    public void givenCredits_WhenSubtractingNonZeroCredits_ThenSubtractValue() {
        Credits credits = Credits.fromDouble(POINT_ZERO_FIVE);
        Credits creditsToSubtract = Credits.fromDouble(POINT_ZERO_FIVE);
        credits.subtract(creditsToSubtract);
        assertEquals(ZERO_REPRESENTATION, credits.valueToString());
    }

}