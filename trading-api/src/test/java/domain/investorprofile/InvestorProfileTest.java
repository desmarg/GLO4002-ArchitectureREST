package domain.investorprofile;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class InvestorProfileTest {

    @Test
    public void whenCreatingNewProfile_thenItIsConservative() {
        ProfileType profileType = ProfileType.CONSERVATIVE;
        List<String> focusAreas = new ArrayList<String>();
        InvestorProfile newInvestorProfile = new InvestorProfile(profileType, focusAreas);
        assertEquals(newInvestorProfile.getProfileType(), ProfileType.CONSERVATIVE);
    }
}
