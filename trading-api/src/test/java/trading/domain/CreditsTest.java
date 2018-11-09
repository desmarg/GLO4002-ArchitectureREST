package trading.domain;

import org.junit.Test;
import trading.domain.Credits.Credits;

import static org.junit.Assert.assertEquals;

public class CreditsTest {

    private static final String ZERO_REPRESENTATION = "0.00";
    private static final String POINT_ONE_REPRESENTATION = "0.10";
    private static final String POINT_25_REPRESENTATION = "0.25";
    private static final long LONG_QUANTITY = 2;
    private static final double DOUBLE_QUANTITY = 2.5;
    private static final double DELTA = 0.01;

    @Test
    public void givenCredits_whenDefaultConstructor_thenValueIsZero() {
        Credits defaultCredits = new Credits();

        assertEquals(ZERO_REPRESENTATION, defaultCredits.toString());
    }

    @Test
    public void givenNewlyCreatedCredits_whenConstructingFromDouble_thenValueHasTwoDecimals() {
        Credits credits = Credits.fromDouble(0.1);

        assertEquals(POINT_ONE_REPRESENTATION, credits.toString());
    }

    @Test
    public void givenCreatedCredits_whenCopyingWithConstructor_thenNewCreditsWithSameValue() {
        Credits credits = Credits.fromDouble(0.1);
        Credits creditsCopy = new Credits(credits);

        assertEquals(credits.toString(), creditsCopy.toString());
    }

    @Test
    public void givenNonZeroCredits_whenMultiplyingWithLongQuantity_thenMultiplyValue() {
        Credits credits = Credits.fromDouble(0.05);

        credits.multiply(LONG_QUANTITY);

        assertEquals(POINT_ONE_REPRESENTATION, credits.toString());
    }

    @Test
    public void givenNonZeroCredits_whenMultiplyingWithDoubleQuantity_thenMultiplyValue() {
        Credits credits = Credits.fromDouble(0.1);

        credits.multiply(DOUBLE_QUANTITY);

        assertEquals(POINT_25_REPRESENTATION, credits.toString());
    }

    @Test
    public void givenCredits_whenAddingNonZeroCredits_thenAddValue() {
        Credits credits = Credits.fromDouble(0.05);
        Credits creditsToAdd = Credits.fromDouble(0.05);

        credits.add(creditsToAdd);

        assertEquals(POINT_ONE_REPRESENTATION, credits.toString());
    }

    @Test
    public void givenCredits_whenSubtractingNonZeroCredits_thenSubtractValue() {
        Credits credits = Credits.fromDouble(0.05);
        Credits creditsToSubtract = Credits.fromDouble(0.05);

        credits.subtract(creditsToSubtract);

        assertEquals(ZERO_REPRESENTATION, credits.toString());
    }

    @Test
    public void givenCreditsWithThirdDecimalLowerThanFive_whenConvertingToFloat_thenReturnHalfWayUp() {
        Credits credits = Credits.fromDouble(0.05);

        assertEquals(0.05, credits.valueToFloat(), DELTA);
    }

    @Test
    public void givenCreditsWithThirdDecimalEqualFive_whenConvertingToFloat_thenReturnHalfWayUp() {
        Credits credits = Credits.fromDouble(0.05);

        assertEquals(0.06, credits.valueToFloat(), DELTA);
    }

    @Test
    public void givenCreditsWithThirdDecimalHigherThanFive_whenConvertingToFloat_thenReturnHalfWayUp() {
        Credits credits = Credits.fromDouble(0.05);

        assertEquals(0.06, credits.valueToFloat(), DELTA);
    }
}
