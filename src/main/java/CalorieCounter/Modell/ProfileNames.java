package CalorieCounter.Modell;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
/**
 * Class representing the foods.
 */
@Entity
public class ProfileNames {
    /**
     * Name of profile.
     */
    @Id
    @Column(name = "ProfileName")
    private String profilename;

    @Override
    public String toString() {
        return "ProfileNames{" +
                "profilename='" + profilename + '\'' +
                '}';
    }

    /**
     * Constructor.
     */
    public ProfileNames(){

    }

    /**
     * Constructor.
     * @param profilename name of profil.
     */
    public ProfileNames(String profilename) {
        this.profilename = profilename;
    }

    /**
     * Get name of profile value.
     * @return name of profile.
     */
    public String getProfilename() {
        return profilename;
    }

    /**
     * Set the name of profil value.
     * @param profilename name of profile.
     */
    public void setProfilename(String profilename) {
        this.profilename = profilename;
    }
}
