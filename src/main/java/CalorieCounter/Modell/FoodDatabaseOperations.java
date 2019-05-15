package CalorieCounter.Modell;



import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Foods database queries.
 */
public class FoodDatabaseOperations {

    private static EntityManager em;

    FoodDatabaseOperations(EntityManager emke){
        em = emke;
    }

    /**
     * Query all foods.
     * @return all Foods list.
     */
    public static List<Foods> allFoods(){

        TypedQuery<Foods> query = em.createQuery("SELECT fd FROM Foods fd",Foods.class);
        List<Foods> result = query.getResultList();

        return result;
    }

    /**
     * Food names to string.
     * @return all food names list.
     */
    public static List<String> FoodNames(){
        List<Foods> result = allFoods();
        List<String> names = new LinkedList<>();
        for(int i=0;i<result.size();i++){
            names.add(i,result.get(i).getFoodname().toLowerCase());
        }
        return names;
    }

    /**
     * Add food to Foods database.
     * @param foodname a food name.
     * @param calorie this food's calorie.
     */
    public static void addFoods(String foodname,int calorie){
        Foods food = new Foods(foodname,calorie);
        if(!FoodNames().contains(food.getFoodname().toLowerCase())) {
            try {
                em.getTransaction().begin();
                em.persist(food);
                em.getTransaction().commit();

            } catch (Exception ex) {
                throw ex;
            }

        }
        else {

            Foods foods = em.find(Foods.class,food.getFoodname());
            em.getTransaction().begin();
            foods.setCalorie(food.getCalorie());
            em.getTransaction().commit();

        }
    }

    /**
     * Query on food with string name.
     * @param name Food name with string.
     * @return if this food exists with this food name or else null.
     */
    public static Foods queryOnefoodByName(String name){
        try {
            TypedQuery<Foods> query = em.createQuery("SELECT fd FROM Foods fd where fd.foodname='"+name+"'",Foods.class);
            List<Foods> result = query.getResultList();
            return result.get(0);
        }
        catch (Exception IndexOutOfBoundsException){
            return null;
        }
    }

    /**
     * Delete a food from Foods database with string name.
     * @param name Food name with string.
     */
    public static void deleteFoods(String name){
        Foods deleted = queryOnefoodByName(name);

        if(deleted!=null) {
            em.getTransaction().begin();
            em.remove(em.contains(deleted) ? deleted : em.merge(deleted));
            em.getTransaction().commit();
        }
    }
}
