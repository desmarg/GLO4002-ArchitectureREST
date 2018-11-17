package trading.domain.datetime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DateTimeTest {

    @Test(expected = MissingDateException.class)
    public void givenNullString_whenInstantiateDateTime_thenThrowMissingDateException() {
        String string = null;
        new DateTime(string);
    }

    @Test(expected = InvalidDateException.class)
    public void givenInvalide_whenInstantiateDateTime_thenThrowInvalidDateException() {
        new DateTime("INVALID");
    }
}