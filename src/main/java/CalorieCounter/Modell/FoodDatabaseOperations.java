package CalorieCounter.Modell;



import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

public class FoodDatabaseOperations {

    private static EntityManagerFactory emf;
    private static EntityManager em;



    public static List<Foods> allFoods(){


        emf = Persistence.createEntityManagerFactory("jpa-persistence-unit-1");
        em = emf.createEntityManager();

        TypedQuery<Foods> query = em.createQuery("SELECT fd FROM Foods fd",Foods.class);
        List<Foods> result = query.getResultList();

        em.close();
        emf.close();

        return result;
    }

    public static List<String> FoodNames(){
        List<Foods> result = allFoods();
        List<String> names = new LinkedList<>();
        for(int i=0;i<result.size();i++){
            names.add(i,result.get(i).getFoodname().toLowerCase());
        }
        return names;
    }


    public static void addFoods(String foodname,int calorie){
        Foods food = new Foods(foodname,calorie);
        if(!FoodNames().contains(food.getFoodname().toLowerCase())) {
            try {
                emf = Persistence.createEntityManagerFactory("jpa-persistence-unit-1");
                em = emf.createEntityManager();
                em.getTransaction().begin();
                em.persist(food);
                em.getTransaction().commit();
                em.close();
                emf.close();
            } catch (Exception ex) {
                throw ex;
            }

        }
        else {
            emf = Persistence.createEntityManagerFactory("jpa-persistence-unit-1");
            em = emf.createEntityManager();
            Foods foods = em.find(Foods.class,food.getFoodname());
            em.getTransaction().begin();
            foods.setCalorie(food.getCalorie());
            em.getTransaction().commit();
            em.close();
            emf.close();
        }
    }

    public static Foods queryOnefoodByName(String name){
        emf = Persistence.createEntityManagerFactory("jpa-persistence-unit-1");
        em = emf.createEntityManager();
        try {
            TypedQuery<Foods> query = em.createQuery("SELECT fd FROM Foods fd where fd.foodname='"+name+"'",Foods.class);
            List<Foods> result = query.getResultList();
            em.close();
            emf.close();

            return result.get(0);
        }
        catch (Exception IndexOutOfBoundsException){
            return null;
        }
    }

    public static void deleteFoods(String name){
        Foods deleted = queryOnefoodByName(name);

        emf = Persistence.createEntityManagerFactory("jpa-persistence-unit-1");
        em = emf.createEntityManager();

        if(deleted!=null) {
            em.getTransaction().begin();
            em.remove(em.contains(deleted) ? deleted : em.merge(deleted));
            em.getTransaction().commit();
            em.close();
            emf.close();
        }
    }
}
