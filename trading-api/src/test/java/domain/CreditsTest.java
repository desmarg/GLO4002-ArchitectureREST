package domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class CreditsTest {

    private static final String ZeroStringRepresentation = "0.00";

    @Test
    public void givenNewlyCreatedCredits_WhenDefaultCunstructor_ThenValueIsZero(){
        Credits defaultCredits = new Credits();
        assertEquals(ZeroStringRepresentation, defaultCredits.valueToString());
    }

}