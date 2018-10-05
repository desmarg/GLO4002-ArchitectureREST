package trading.domain.investorprofile;

import org.junit.Test;
import trading.domain.FocusArea;
import trading.domain.InvestorProfile;
import trading.domain.ProfileType;

import java.util.ArrayList;
import java.util.List;

public class InvestorProfileTest {

    @Test
    public void whenCreatingNewProfile_thenItIsConservative() {
        ProfileType profileType = ProfileType.CONSERVATIVE;
        List<FocusArea> focusAreas = new ArrayList<>();
        InvestorProfile newInvestorProfile = new InvestorProfile(profileType, focusAreas);
        assertEquals(newInvestorProfile.getProfileType(), ProfileType.CONSERVATIVE);
    }
}
