package domain.investorprofile;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class InvestorProfileTest {

    @Test
    public void whenCreatingNewProfile_thenItIsConservative(){
        ProfileType profileType = ProfileType.CONSERVATIVE;
        List<String> focusAreas = new ArrayList<String>();
        InvestorProfile newInvestorProfile = new InvestorProfile(profileType, focusAreas);
        assertEquals(newInvestorProfile.getProfileType(), ProfileType.CONSERVATIVE);
    }
}
