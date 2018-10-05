package trading.domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InvestorProfileTest {

    @Test
    public void whenCreatingNewProfile_thenItIsConservative() {
        ProfileType profileType = ProfileType.CONSERVATIVE;
        List<FocusArea> focusAreas = new ArrayList<>();
        InvestorProfile newInvestorProfile = new InvestorProfile(profileType, focusAreas);
        assertEquals(newInvestorProfile.getProfileType(), ProfileType.CONSERVATIVE);
    }
}
