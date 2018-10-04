package domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreditsTest {

    private static final String ZERO_REPRESENTATION = "0.00";
    private static final String POINT_ONE_REPRESENTATION = "0.10";
    private static final double POINT_ONE = 0.1;
    private static final double POINT_ZERO_FIZE = 0.05;
    private static final long DOUBLE_QUANTITY = 2;


    @Test
    public void givenNewlyCreatedCredits_WhenDefaultCunstructor_ThenValueIsZero() {
        Credits defaultCredits = new Credits();
        assertEquals(ZERO_REPRESENTATION, defaultCredits.valueToString());
    }

    @Test
    public void givenNewlyCreatedCredits_WhenConstructingFromFloat_ThenValueHasTwoDecimals() {
        Credits credits = Credits.fromDouble(POINT_ONE);
        assertEquals(POINT_ONE_REPRESENTATION, credits.valueToString());
    }

//    @Test
//    public void givenNonZeroCredits_WhenMultiplyingWithQuantity_ThenMultiplyValue(){
//        Credits credits = Credits.fromDouble(POINT_ZERO_FIZE);
//        assertEquals(POINT_ONE_REPRESENTATION, credits.multiply(DOUBLE_QUANTITY).valueToString());
//    }
//
//
//    @Test
//    public void givenCredits_WhenAddingNonZeroCredits_ThenAddValue(){
//        Credits credits = Credits.fromDouble(POINT_ZERO_FIZE);
//        Credits creditsToAdd = Credits.fromDouble(POINT_ZERO_FIZE);
//        assertEquals(POINT_ONE_REPRESENTATION, credits.add(creditsToAdd).valueToString());
//    }
//
//    @Test
//    public void givenCredits_WhenSubtractingNonZeroCredits_ThenSubtractValue(){
//        Credits credits = Credits.fromDouble(POINT_ZERO_FIZE);
//        Credits creditsToSubtract = Credits.fromDouble(POINT_ZERO_FIZE);
//        assertEquals(ZERO_REPRESENTATION, credits.subtract(creditsToSubtract).valueToString());
//    }


}