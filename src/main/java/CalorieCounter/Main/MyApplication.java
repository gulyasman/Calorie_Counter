package CalorieCounter.Main;

import CalorieCounter.Modell.UserDAOfactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Application extends.
 */
public class MyApplication extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {
        Main.CurrentStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/profileLogin.fxml"));
        Scene scene = new Scene(root);
        Main.CurrentStage.setScene(scene);
        Main.CurrentStage.show();
        Main.CurrentStage.setOnCloseRequest(e->closeApp());
    }

    private static void closeApp() {
        UserDAOfactory.getInstance().close();
    }

}
