package CalorieCounter.Controller;

import CalorieCounter.Main;
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

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class profileLoginController implements Initializable {

    @FXML
    private TextField profileName;
    @FXML
    private ChoiceBox choiceBox;
    @FXML
    private Label Warninglabel;

    public static String currentProfil;



    public void exitEvent(ActionEvent event){
        Main.CurrentStage.close();
    }
    public void next(ActionEvent event) throws IOException {
        if(!profileName.getText().isEmpty()) {
            if (!ProfileNamesDatabaseOperations.isInProfileNames(profileName.getText())) {
                ProfileNamesDatabaseOperations.addProfileName(profileName.getText());
            }
            currentProfil = profileName.getText();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/main.fxml"));
            Parent root = loader.load();
            Main.CurrentStage.setScene(new Scene(root));
        }
        else {
            Warninglabel.setText("Warning! : Empty profile");
            Warninglabel.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);\n" +
                    "    -fx-background-radius: 30;\n" +
                    "    -fx-background-insets: 0;\n" +
                    "    -fx-text-fill: white;");

        }

    }

    public void choiceBoxevent(ActionEvent event){
        profileName.setText(choiceBox.getValue().toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (String i:ProfileNamesDatabaseOperations.allProfileNametoString()) {
            choiceBox.getItems().add(i);
        }
    }
}
