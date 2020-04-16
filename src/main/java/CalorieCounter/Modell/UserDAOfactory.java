package CalorieCounter.Modell;

import org.jasypt.util.text.StrongTextEncryptor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to make {@code UserDAOFactory} objects and connection with the database.
 */
public class UserDAOfactory {
    /**
     * The current instance of this object.
     */
    private static UserDAOfactory instance;
    /**
     * The {@code EntityManager} of the {@code UserDAO}.
     */
    private static EntityManager em;
    /**
     * The {@code EntityManagerFactory} of to create an {@code EntityManager}.
     */
    private static EntityManagerFactory f;
    /**
     * Creates a new instance of this object.
     */
    static {
        instance = new UserDAOfactory(em);
        f = Persistence.createEntityManagerFactory("jpa-persistence-unit-1");
        em = f.createEntityManager();
    }
    /**
     * Constructs a {@code UserDAOFactory} object.
     * @param em
     */
    private UserDAOfactory(EntityManager em){ }
    /**
     * Returns an instance of the factory.
     * @return an instance of the factory.
     */
    public static UserDAOfactory getInstance(){
        return instance;
    }
    /**
     * Creates a new {@code UserDAO} object.
     * @return a {@code UserDAO} object
     * @see UserDAOfactory
     */
    public ProfileNamesDatabaseOperations createProfilNameDAO(){
        return new ProfileNamesDatabaseOperations(em);
    }

    /**
     * Create a new FoodDatabase entity manager.
     * @return FoodDatabase entity manager.
     */
    public FoodDatabaseOperations createFoodDAO(){
        return new FoodDatabaseOperations(em);
    }

    /**
     * Closes the database connection.
     */
    public void close(){
        em.close();
        f.close();
    }
}