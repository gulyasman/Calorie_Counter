package CalorieCounter.Modell;

import javax.persistence.*;

@Entity
public class Profiles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Profileid", updatable = false, nullable = false)
    private Long profileId;

    @Column(name = "ProfileName")
    private String profileName;

    public Profiles(){

    }

    public Profiles(String profilename) {
        this.profileName = profilename;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
}
