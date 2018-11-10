package trading.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class InvestorProfile {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private ProfileType profileType;
    @ElementCollection
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
