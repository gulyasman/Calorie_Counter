package CalorieCounter.Modell;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;

/**
 * ProfileNames database queries.
 */
public class ProfileNamesDatabaseOperations {

    private static EntityManager em;

    ProfileNamesDatabaseOperations(EntityManager emke){
        em = emke;
    }

    /**
     * Add a profile name to ProfileNames database.
     * @param add Profile name string.
     */
    public static void addProfileName(String add){
        ProfileNames adder = new ProfileNames(add);

        em.getTransaction().begin();
        em.persist(adder);
        em.getTransaction().commit();


    }

    /**
     * Query all profile name.
     * @return List of all profile name.
     */
    public static List<ProfileNames> allProfileName(){

        TypedQuery<ProfileNames> query = em.createQuery("SELECT pf FROM ProfileNames pf",ProfileNames.class);
        List<ProfileNames> result = query.getResultList();

        return result;
    }

    /**
     * Query all profile name in string format.
     * @return List of all profile name string.
     */
    public static List<String> allProfileNametoString(){
        List<String> stringNames = new LinkedList<>();
        List<ProfileNames> result = allProfileName();

        for(int i=0; i<result.size();i++)
            stringNames.add(i,result.get(i).getProfilename());

        return stringNames;
    }

    /**
     * Boolean profile in all profile.
     * @param profilname Name of profile in string format.
     * @return profile name is in profiles.
     */
    public static boolean isInProfileNames(String profilname){

        ProfileNames resultname = new ProfileNames(profilname);

        if(allProfileNametoString().contains(profilname))
            return true;
        else
            return false;
    }
}
