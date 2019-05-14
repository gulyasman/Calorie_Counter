package CalorieCounter.Modell;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;

public class ProfileNamesDatabaseOperations {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static void addProfileName(String add){
        ProfileNames adder = new ProfileNames(add);
        emf = Persistence.createEntityManagerFactory("jpa-persistence-unit-1");
        em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(adder);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }

    public static List<ProfileNames> allProfileName(){
        emf = Persistence.createEntityManagerFactory("jpa-persistence-unit-1");
        em = emf.createEntityManager();

        TypedQuery<ProfileNames> query = em.createQuery("SELECT pf FROM ProfileNames pf",ProfileNames.class);
        List<ProfileNames> result = query.getResultList();

        em.close();
        emf.close();

        return result;
    }

    public static List<String> allProfileNametoString(){
        List<String> stringNames = new LinkedList<>();
        List<ProfileNames> result = allProfileName();

        for(int i=0; i<result.size();i++)
            stringNames.add(i,result.get(i).getProfilename());

        return stringNames;
    }

    public static boolean isInProfileNames(String profilname){

        ProfileNames resultname = new ProfileNames(profilname);

        if(allProfileNametoString().contains(profilname))
            return true;
        else
            return false;
    }
}
