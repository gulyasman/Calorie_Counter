package CalorieCounter.Modell;

import java.util.List;

/**
 * Class for counting calorie numbers.
 */
public class CalorieCounting {
    /**
     * Calorie intake counter.
     * @param food foods of lists with Foods database items.
     * @return sum of calories.
     */
    public static int sum(List<Foods> food){
        return food.stream().mapToInt(a -> a.getCalorie()).sum();
    }

    /**
     * Max calorie intake number counter.
     * @param sex sex of profile.
     * @param weight weight of profile.
     * @return Max of calorie in a day.
     */
    public static int maxCalorieIntake(String sex,int weight){
        if(sex.equals("Man"))
            return 900+10*weight;
        else
            return 700+7*weight;
    }

    /**
     * Difference counter.
     * @param myCalorie profile Calorie.
     * @param borderClorie Max of calorie in a day.
     * @return Difference in profile calorie and max calorie of day.
     */
    public static int differenceCounter(int myCalorie,int borderClorie){
        return borderClorie-myCalorie;
    }
}
