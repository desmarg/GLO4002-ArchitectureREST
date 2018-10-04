package domain.investorprofile;

import java.util.List;

public class InvestorProfile {

    private ProfileType profileType;
    private List<FocusArea> focusAreas;

    public InvestorProfile(ProfileType profileType, List<FocusArea> focusAreas) {
        this.profileType = profileType;
        this.focusAreas = focusAreas;
    }

    public ProfileType getProfileType() {
        return this.profileType;
    }

    public List<FocusArea> getFocusAreas() {
        return this.focusAreas;
    }
}
