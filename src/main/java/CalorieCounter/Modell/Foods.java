package CalorieCounter.Modell;

import javax.persistence.*;

@Entity
public class Foods {


    @Id
    @Column(name = "Foodname")
    private String foodname;

    @Column(name = "Calorie")
    private int calorie;

    public Foods(){

    }

    public Foods(String foodname, int calorie) {
        this.foodname = foodname;
        this.calorie = calorie;
    }


    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    @Override
    public String toString() {
        return foodname+" : "+calorie+" kcal";
    }
}
