package domain.investorprofile;

import java.util.ArrayList;
import java.util.List;

public class InvestorProfile {

    private ProfileType profileType;
    private List<String> focusAreas;

    public InvestorProfile(){
        this.profileType = ProfileType.CONSERVATIVE;
        this.focusAreas = new ArrayList<>();
    }

    public ProfileType getProfileType() {
        return profileType;
    }
}