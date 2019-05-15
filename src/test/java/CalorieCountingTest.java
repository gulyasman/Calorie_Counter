import CalorieCounter.Modell.CalorieCounting;
import CalorieCounter.Modell.Foods;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalorieCountingTest {

    private Foods food1;
    private Foods food2;
    private Foods food3;
    private Foods food4;
    private Foods food5;

    private List<Foods> ListfoodTest1;
    private List<Foods> ListfoodTest2;
    private List<Foods> ListfoodTest3;
    private List<Foods> ListfoodTest4;
    private List<Foods> ListfoodTest5;

    @BeforeEach
    void setUp() {
        food1 = new Foods("Apple",20);
        food2 = new Foods("Meal",40);
        food3 = new Foods("Banana",60);
        food4 = new Foods("CheeseBurger",373);
        food5 = new Foods("Milk",87);

        ListfoodTest1 = Arrays.asList(food1,food2,food4);
        ListfoodTest2 = Arrays.asList(food1,food4);
        ListfoodTest3 = Arrays.asList(food1,food2,food4,food2,food5);
        ListfoodTest4 = Arrays.asList(food1,food2,food4,food3,food5);
        ListfoodTest5 = Arrays.asList(food1,food2,food4,food2,food5,food5,food2);
    }

    @AfterEach
    void tearDown() {
        food1 = null;
        food2 = null;
        food3 = null;
        food4 = null;
        food5 = null;

        ListfoodTest1 = null;
        ListfoodTest2 = null;
        ListfoodTest3 = null;
        ListfoodTest4 = null;
        ListfoodTest5 = null;
    }

    @Test
        void differenceCounter(){
            assertEquals(1000, CalorieCounting.differenceCounter(1000,2000),"Wrong difference");
            assertEquals(2000, CalorieCounting.differenceCounter(1000,3000),"Wrong difference");
            assertEquals(5000, CalorieCounting.differenceCounter(1000,6000),"Wrong difference");
            assertEquals(6734, CalorieCounting.differenceCounter(852,7586),"Wrong difference");
            assertEquals(600, CalorieCounting.differenceCounter(1000,1600),"Wrong difference");
            assertEquals(-1000, CalorieCounting.differenceCounter(2000,1000),"Wrong difference");
            assertEquals(-10000, CalorieCounting.differenceCounter(20000,10000),"Wrong difference");
            assertEquals(-313, CalorieCounting.differenceCounter(1543,1230),"Wrong difference");
        }
        @Test
        void maxCalorieIntake(){
            assertEquals(900,CalorieCounting.maxCalorieIntake("Man",0),"Wrong maxCalorie");
            assertEquals(910,CalorieCounting.maxCalorieIntake("Man",1),"Wrong maxCalorie");
            assertEquals(700,CalorieCounting.maxCalorieIntake("Woman",0),"Wrong maxCalorie");
            assertEquals(707,CalorieCounting.maxCalorieIntake("Woman",1),"Wrong maxCalorie");
            assertEquals(1400,CalorieCounting.maxCalorieIntake("Woman",100),"Wrong maxCalorie");
            assertEquals(1295,CalorieCounting.maxCalorieIntake("Woman",85),"Wrong maxCalorie");
            assertEquals(1900,CalorieCounting.maxCalorieIntake("Man",100),"Wrong maxCalorie");
            assertEquals(2020,CalorieCounting.maxCalorieIntake("Man",112),"Wrong maxCalorie");
        }
        @Test
        void sum(){
            assertEquals(433,CalorieCounting.sum(ListfoodTest1),"Wrong sum");
            assertEquals(393,CalorieCounting.sum(ListfoodTest2),"Wrong sum");
            assertEquals(560,CalorieCounting.sum(ListfoodTest3),"Wrong sum");
            assertEquals(580,CalorieCounting.sum(ListfoodTest4),"Wrong sum");
            assertEquals(687,CalorieCounting.sum(ListfoodTest5),"Wrong sum");
        }
}
