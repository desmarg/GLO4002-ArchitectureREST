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
    public void givenNewlyCreatedCredits_whenDefaultConstructor_thenValueIsZero() {
        Credits defaultCredits = new Credits();

        assertEquals(ZERO_REPRESENTATION, defaultCredits.toString());
    }

    @Test
    public void givenNewlyCreatedCredits_whenConstructingFromDouble_thenValueHasTwoDecimals() {
        Credits credits = Credits.fromDouble(POINT_ONE);

        assertEquals(POINT_ONE_REPRESENTATION, credits.toString());
    }

    @Test
    public void givenCreatedCredits_whenCopyingWithConstructor_thenNewCreditsWithSameValue() {
        Credits credits = Credits.fromDouble(POINT_ONE);
        Credits creditsCopy = new Credits(credits);

        assertEquals(credits.toString(), creditsCopy.toString());
    }

    @Test
    public void givenNonZeroCredits_whenMultiplyingWithLongQuantity_thenMultiplyValue() {
        Credits credits = Credits.fromDouble(POINT_ZERO_FIVE);

        credits.multiply(LONG_QUANTITY);

        assertEquals(POINT_ONE_REPRESENTATION, credits.toString());
    }

    @Test
    public void givenNonZeroCredits_whenMultiplyingWithDoubleQuantity_thenMultiplyValue() {
        Credits credits = Credits.fromDouble(POINT_ONE);

        credits.multiply(DOUBLE_QUANTITY);

        assertEquals(POINT_25_REPRESENTATION, credits.toString());
    }

    @Test
    public void givenCredits_whenAddingNonZeroCredits_thenAddValue() {
        Credits credits = Credits.fromDouble(POINT_ZERO_FIVE);
        Credits creditsToAdd = Credits.fromDouble(POINT_ZERO_FIVE);

        credits.add(creditsToAdd);

        assertEquals(POINT_ONE_REPRESENTATION, credits.toString());
    }

    @Test
    public void givenCredits_whenSubtractingNonZeroCredits_thenSubtractValue() {
        Credits credits = Credits.fromDouble(POINT_ZERO_FIVE);
        Credits creditsToSubtract = Credits.fromDouble(POINT_ZERO_FIVE);

        credits.subtract(creditsToSubtract);

        assertEquals(ZERO_REPRESENTATION, credits.toString());
    }

    @Test
    public void givenCreditsWithThirdDecimalLowerThanFive_whenConvertingToFloat_thenReturnHalfWayUp() {
        Credits credits = Credits.fromDouble(POINT_ZERO_FIVE_ONE);

        assertEquals(POINT_ZERO_FIVE, credits.valueToFloat(), 0.01);
    }

    @Test
    public void givenCreditsWithThirdDecimalEqualFive_whenConvertingToFloat_thenReturnHalfWayUp() {
        Credits credits = Credits.fromDouble(POINT_ZERO_FIVE_FIVE);

        assertEquals(POINT_ZERO_SIX, credits.valueToFloat(), 0.01);
    }

    @Test
    public void givenCreditsWithThirdDecimalHigherThanFive_whenConvertingToFloat_thenReturnHalfWayUp() {
        Credits credits = Credits.fromDouble(POINT_ZERO_FIVE_NINE);

        assertEquals(POINT_ZERO_SIX, credits.valueToFloat(), 0.01);
    }
}
