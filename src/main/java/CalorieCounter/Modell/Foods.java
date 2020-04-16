package CalorieCounter.Modell;

import javax.persistence.*;

/**
 * Class representing the foods.
 */
@Entity
public class Foods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Foodid", updatable = false, nullable = false)
    private Long foodid;

    /**
     * Name of food.
     */
    @Column(name = "Foodname")
    private String foodname;
    /**
     * Calorie of food.
     */
    @Column(name = "Calorie")
    private int calorie;

    /**
     * Constructor.
     */
    public Foods(){

    }

    /**
     * Constructor.
     * @param foodname Name of food.
     * @param calorie Calorie of food.
     */
    public Foods(String foodname, int calorie) {
        this.foodname = foodname;
        this.calorie = calorie;
    }

    /**
     * Get the name of food.
     * @return Name of food.
     */
    public String getFoodname() {
        return foodname;
    }

    /**
     * Set the name of food value.
     * @param foodname Name of food.
     */
    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    /**
     * Get the calorie of food.
      * @return Calorie of food.
     */
    public int getCalorie() {
        return calorie;
    }

    /**
     * set the calorie of food value.
     * @param calorie calorie of food.
     */
    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    @Override
    public String toString() {
        return foodname+" : "+calorie+" kcal";
    }
}
