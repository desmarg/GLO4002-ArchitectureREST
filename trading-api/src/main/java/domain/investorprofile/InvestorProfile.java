package domain.investorprofile;

import java.util.List;

public class InvestorProfile {

    private ProfileType profileType;
    private List<String> focusAreas;

    public InvestorProfile(ProfileType profileType, List<String> focusAreas) {
        this.profileType = profileType;
        this.focusAreas = focusAreas;
    }

    public ProfileType getProfileType() {
        return this.profileType;
    }

    public List<String> getFocusAreas() {
        return this.focusAreas;
    }
}
