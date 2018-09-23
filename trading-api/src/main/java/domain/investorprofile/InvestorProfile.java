package domain.investorprofile;

import java.util.ArrayList;

public class InvestorProfile {

    private ProfileType profileType;
    private ArrayList<String> focusAreas;

    public InvestorProfile(){
        this.profileType = ProfileType.CONSERVATIVE;
        this.focusAreas = new ArrayList<>();
    }

    public ProfileType getProfileType() {
        return profileType;
    }

    public ArrayList<String> getFocusAreas() {
        return focusAreas;
    }
}