package trading.domain;

import java.util.List;

public class InvestorProfile {
    private final ProfileType profileType;
    private final List<String> focusAreas;

    public InvestorProfile(final ProfileType profileType, final List<String> focusAreas) {
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
