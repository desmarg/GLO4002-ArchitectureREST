package domain.investorprofile;

import java.util.List;
import java.security.InvalidParameterException;

public class InvestorProfile {

    private ProfileType profileType;
    private List<String> focusAreas;

    public InvestorProfile(ProfileType profileType, List<String> focusAreas) {
        if (profileType == null) {
            throw new InvalidParameterException("profileType cannot be null");
        }

        if (focusAreas == null) {
            throw new InvalidParameterException("focusArea cannot be null");
        }
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
