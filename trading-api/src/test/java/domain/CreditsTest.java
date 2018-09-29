package domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class CreditsTest {

    private static final String ZERO_REPRESENTATION = "0.00";
    private static final String POINT_ONE_REPRESENTATION = "0.10";
    private static final double  POINT_ONE = 0.1;
    private static final double  POINT_ZERO_FIZE = 0.05;
    private static final long  DOUBLE_QUANTITY = 2;


    @Test
    public void givenNewlyCreatedCredits_WhenDefaultCunstructor_ThenValueIsZero(){
        Credits defaultCredits = new Credits();
        assertEquals(ZERO_REPRESENTATION, defaultCredits.valueToString());
    }

    @Test
    public void givenNewlyCreatedCredits_WhenConstructingFromFloat_ThenValueHasTwoDecimals(){
        Credits credits = Credits.fromFloat(POINT_ONE);
        assertEquals(POINT_ONE_REPRESENTATION, credits.valueToString());
    }

    @Test
    public void givenNonZeroCredits_WhenMultiplyingWithQuantity_ThenMultiplyValue(){
        Credits credits = Credits.fromFloat(POINT_ZERO_FIZE);
        credits.multiply(DOUBLE_QUANTITY);
        assertEquals(POINT_ONE_REPRESENTATION, credits.valueToString());
    }


    @Test
    public void givenCredits_WhenAddingNonZeroCredits_ThenAddValue(){
        Credits credits = Credits.fromFloat(POINT_ZERO_FIZE);
        Credits creditsToAdd = Credits.fromFloat(POINT_ZERO_FIZE);
        credits.add(creditsToAdd);
        assertEquals(POINT_ONE_REPRESENTATION, credits.valueToString());
    }

    @Test
    public void givenCredits_WhenSubtractingNonZeroCredits_ThenSubtractValue(){
        Credits credits = Credits.fromFloat(POINT_ZERO_FIZE);
        Credits creditsToSubtract = Credits.fromFloat(POINT_ZERO_FIZE);
        credits.subtract(creditsToSubtract);
        assertEquals(ZERO_REPRESENTATION, credits.valueToString());
    }


}