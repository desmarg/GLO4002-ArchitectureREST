package trading.domain;

import java.util.List;

public class InvestorProfile {
    private final ProfileType type;
    private final List<String> focusAreas;

    public InvestorProfile(final ProfileType type, final List<String> focusAreas) {
        this.type = type;
        this.focusAreas = focusAreas;
    }

    public ProfileType getType() {
        return this.type;
    }

    public List<String> getFocusAreas() {
        return this.focusAreas;
    }
}
