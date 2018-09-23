package domain.investorprofile;

import org.junit.Test;

import static org.junit.Assert.*;

public class InvestorProfileTest {

    @Test
    public void whenCreatingNewProfile_thenItIsConservative(){
        InvestorProfile newInvestorProfile = new InvestorProfile();

        assertEquals(newInvestorProfile.getProfileType(), ProfileType.CONSERVATIVE);
    }

}