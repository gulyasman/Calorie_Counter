package CalorieCounter.Modell;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProfileNames {
    @Id
    @Column(name = "ProfileName")
    private String profilename;

    @Override
    public String toString() {
        return "ProfileNames{" +
                "profilename='" + profilename + '\'' +
                '}';
    }

    public ProfileNames(){

    }
    public ProfileNames(String profilename) {
        this.profilename = profilename;
    }

    public String getProfilename() {
        return profilename;
    }

    public void setProfilename(String profilename) {
        this.profilename = profilename;
    }
}
