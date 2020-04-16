package CalorieCounter.Controller;

import CalorieCounter.Main.Main;
import CalorieCounter.Modell.FoodDatabaseOperations;
import CalorieCounter.Modell.Profiles;
import CalorieCounter.Modell.UserDAOfactory;
import CalorieCounter.Modell.ProfileNamesDatabaseOperations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class for controlling the profiles scene's actions.
 */
public class profileLoginController implements Initializable {
    /**
     * The logger for this class.
     */
    private static Logger logger = LoggerFactory.getLogger(mainfxmlController.class);
    @FXML
    private TextField profileName;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private Label Warninglabel;

    private ProfileNamesDatabaseOperations pLC;
    private FoodDatabaseOperations FDO;

    /**
     * The user profil name from {@code ProfilNames} database.
     */
    public static Profiles currentProfil;


    /**
     * Close application button event.
     * @param event actionEvent the event that occurred.
     * @see javafx.event.ActionEvent
     */
    public void exitEvent(ActionEvent event){
        Main.CurrentStage.close();
    }

    /**
     * Next button event for step into mainfxml.
     */
    public void next(){
        if(!profileName.getText().isEmpty()) {
            if (!ProfileNamesDatabaseOperations.isInProfileNames(profileName.getText())) {
                ProfileNamesDatabaseOperations.addProfileName(profileName.getText());
            }
            currentProfil = ProfileNamesDatabaseOperations.oneProfileByString(profileName.getText());
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/main.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.info("next butten is pressed");
            Main.CurrentStage.setScene(new Scene(root));
            Main.CurrentStage.setResizable(false);
        }
        else {
            Warninglabel.setText("Warning! : Empty profile");
            Warninglabel.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);\n" +
                    "    -fx-background-radius: 30;\n" +
                    "    -fx-background-insets: 0;\n" +
                    "    -fx-text-fill: white;");

        }

    }

    /**
     * Set profileName textfield with choiceBox value.
     */
    public void choiceBoxevent(){
        profileName.setText(choiceBox.getValue().toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pLC = UserDAOfactory.getInstance().createProfilNameDAO();
        FDO = UserDAOfactory.getInstance().createFoodDAO();
        for (String i:ProfileNamesDatabaseOperations.allProfileNametoString()) {
            choiceBox.getItems().add(i);
        }
    }
}
