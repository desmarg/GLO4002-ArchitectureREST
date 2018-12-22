package trading.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class CreditsTest {

    private static final Long LONG_QUANTITY = 2L;

    @Test
    public void givenCredits_whenDefaultConstructor_thenValueIsZero() {
        Credits defaultCredits = Credits.fromInteger(0, Currency.CAD);

        assertEquals("0.00", defaultCredits.toString());
    }

    @Test
    public void givenNewlyCreatedCredits_whenConstructingFromString_thenValueHasTwoDecimals() {
        Credits credits = Credits.fromString("0.1", Currency.CAD);
        assertEquals("0.10", credits.toString());
    }

    @Test
    public void givenCreatedCredits_whenCopyingWithConstructor_thenNewCreditsWithSameValue() {
        Credits credits = Credits.fromString("0.1", Currency.CAD);

        assertEquals(credits.toString(), credits.toString());
    }

    @Test
    public void givenNonZeroCredits_whenMultiplyingWithLongQuantity_thenMultiplyValue() {
        Credits credits = Credits.fromString("0.05", Currency.CAD);

        credits = credits.multiply(Credits.fromLong(LONG_QUANTITY, Currency.CAD));

        assertEquals("0.10", credits.toString());
    }

    @Test
    public void givenCredits_whenAddingNonZeroCredits_thenAddValue() {
        Credits credits = Credits.fromString("0.05", Currency.CAD).add(Credits.fromString("0.07", Currency.CAD));

        assertEquals("0.12", credits.toString());
    }

    @Test
    public void givenCredits_whenSubtractingNonZeroCredits_thenSubtractValue() {
        Credits credits = Credits.fromString("0.05", Currency.CAD).subtract(Credits.fromString("0.05", Currency.CAD));

        assertEquals("0.00", credits.toString());
    }
}
