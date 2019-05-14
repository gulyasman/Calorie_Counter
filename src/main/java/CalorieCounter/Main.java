package CalorieCounter;

import CalorieCounter.Modell.FoodDatabaseOperations;
import CalorieCounter.Modell.Foods;
import CalorieCounter.Modell.ProfileNames;
import CalorieCounter.Modell.ProfileNamesDatabaseOperations;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;


public class Main extends Application {

    public static Stage CurrentStage;

    public static void main(String[] args) {
                launch(args);
            }

    @Override
    public void start(Stage primaryStage) throws IOException {
        CurrentStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/profileLogin.fxml"));
        Scene scene = new Scene(root);
        CurrentStage.setScene(scene);
        CurrentStage.show();
    }
}
