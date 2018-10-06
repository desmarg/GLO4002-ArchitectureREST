package trading.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreditsTest {

    private static final String ZERO_REPRESENTATION = "0.00";
    private static final String POINT_ONE_REPRESENTATION = "0.10";
    private static final String POINT_25_REPRESENTATION = "0.25";
    private static final double POINT_ONE = 0.1;
    private static final double POINT_ZERO_FIVE = 0.05;
    private static final double POINT_ZERO_SIX = 0.06;
    private static final double POINT_ZERO_FIVE_ONE = 0.051;
    private static final double POINT_ZERO_FIVE_FIVE = 0.055;
    private static final double POINT_ZERO_FIVE_NINE = 0.059;
    private static final long LONG_QUANTITY = 2;
    private static final double DOUBLE_QUANTITY = 2.5;

    @Test
    public void givenNewlyCreatedCredits_WhenDefaultConstructor_ThenValueIsZero() {
        Credits defaultCredits = new Credits();
        assertEquals(ZERO_REPRESENTATION, defaultCredits.valueToString());
    }

    @Test
    public void givenNewlyCreatedCredits_WhenConstructingFromDouble_ThenValueHasTwoDecimals() {
        Credits credits = Credits.fromDouble(POINT_ONE);
        assertEquals(POINT_ONE_REPRESENTATION, credits.valueToString());
    }

    @Test
    public void givenCreatedCredits_WhenCopyingWithConstructor_ThenNewCreditsWithSameValue() {
        Credits credits = Credits.fromDouble(POINT_ONE);
        Credits creditsCopy = new Credits(credits);
        assertEquals(credits.valueToString(), creditsCopy.valueToString());
    }

    @Test
    public void givenNonZeroCredits_WhenMultiplyingWithLongQuantity_ThenMultiplyValue() {
        Credits credits = Credits.fromDouble(POINT_ZERO_FIVE);
        credits.multiply(LONG_QUANTITY);
        assertEquals(POINT_ONE_REPRESENTATION, credits.valueToString());
    }

    @Test
    public void givenNonZeroCredits_WhenMultiplyingWithDoubleQuantity_ThenMultiplyValue() {
        Credits credits = Credits.fromDouble(POINT_ONE);
        credits.multiply(DOUBLE_QUANTITY);
        assertEquals(POINT_25_REPRESENTATION, credits.valueToString());
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

    @Test
    public void givenCreditsWithThirdDecimalLowerThanFive_WhenConvertingToFloat_ThenReturnHalfWayUp() {
        Credits credits = Credits.fromDouble(POINT_ZERO_FIVE_ONE);
        assertEquals(POINT_ZERO_FIVE, credits.valueToFloat(), 0.01);
    }

    @Test
    public void givenCreditsWithThirdDecimalEqualFive_WhenConvertingToFloat_ThenReturnHalfWayUp() {
        Credits credits = Credits.fromDouble(POINT_ZERO_FIVE_FIVE);
        assertEquals(POINT_ZERO_SIX, credits.valueToFloat(), 0.01);
    }

    @Test
    public void givenCreditsWithThirdDecimalHigherThanFive_WhenConvertingToFloat_ThenReturnHalfWayUp() {
        Credits credits = Credits.fromDouble(POINT_ZERO_FIVE_NINE);
        assertEquals(POINT_ZERO_SIX, credits.valueToFloat(), 0.01);
    }



}