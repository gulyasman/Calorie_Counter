package CalorieCounter.Modell;

import java.util.List;

public class CalorieCounting {

    public static int sum(List<Foods> food){
        return food.stream().mapToInt(a -> a.getCalorie()).sum();
    }

    public static int maxCalorieIntake(String sex,int weight){
        if(sex.equals("Man"))
            return 900+10*weight;
        else
            return 700+7*weight;
    }

    public static int differenceCounter(int myCalorie,int borderClorie){
        return borderClorie-myCalorie;
    }
}
