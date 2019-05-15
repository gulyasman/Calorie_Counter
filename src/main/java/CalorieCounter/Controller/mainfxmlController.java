package CalorieCounter.Controller;

import CalorieCounter.Main.Main;
import CalorieCounter.Modell.CalorieCounting;
import CalorieCounter.Modell.FoodDatabaseOperations;
import CalorieCounter.Modell.Foods;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Class for controlling the main scene's actions.
 */
public class mainfxmlController implements Initializable {

    @FXML
    private Label ProfilNameLabel;

    @FXML
    private ChoiceBox foodsChoiceBox;

    @FXML
    private TextField foodnameTextfield;

    @FXML
    private TextField kcalTextfield;

    @FXML
    private ListView myfoodLists;

    @FXML
    private Label CalorieNumberLabel;

    @FXML
    private RadioButton manButton;

    @FXML
    private RadioButton womanButton;

    @FXML
    private TextField weightTextfield;

    @FXML
    private Label profileKcal;

    @FXML
    private Label calorieDifferenceLabel;

    private List<Foods> countingFoods = new LinkedList<>();

    /**
     * This method set the text - color
     * based on maxkcal and own kcal difference.
     */
    public void differenceObserver(){
        if(!weightTextfield.getText().isEmpty()){
        if(CalorieCounting.sum(countingFoods)<=Integer.parseInt(profileKcal.getText())){
            CalorieNumberLabel.setStyle("-fx-text-fill: green;-fx-background-color: grey");
            calorieDifferenceLabel.setStyle("-fx-text-fill: green;-fx-background-color: grey");
        }
        else {
            CalorieNumberLabel.setStyle("-fx-text-fill: red;-fx-background-color: grey");
            calorieDifferenceLabel.setStyle("-fx-text-fill: red;-fx-background-color: grey");
        }
        calorieDifferenceLabel.setText(Integer.toString(CalorieCounting.differenceCounter(CalorieCounting.sum(countingFoods), Integer.parseInt(profileKcal.getText()))));
        }
    }

    /**
     * This method upload the foodsChoiceBox
     * with {@code Foods} database elements.
     */
    public void choiceBoxDataCreate(){
        foodsChoiceBox.getItems().clear();
        for (Foods i: FoodDatabaseOperations.allFoods()) {
            foodsChoiceBox.getItems().add(i);
        }
        foodnameTextfield.setText("");
        kcalTextfield.setText("");
    }

    /**
     * onAction event of Back button
     * and take it back to profiles scene.
     * @param event actionEvent the event that occurred.
     * @see javafx.event.ActionEvent
     */
    public void back(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/profileLogin.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.CurrentStage.setScene(new Scene(root));
    }

    /**
     * onAction event of addFoodbutton
     * and add a {@code Foods} to database.
     */
    public void addFoodbutton(){
        if(!foodnameTextfield.getText().isEmpty()&&!kcalTextfield.getText().isEmpty()) {
            FoodDatabaseOperations.addFoods(foodnameTextfield.getText(), Integer.parseInt(kcalTextfield.getText()));
            choiceBoxDataCreate();
        }
    }

    /**
     * onAction event of deleteFoodbutton
     * and delete a food from {@code Foods}database.
     */
    public void deleteFoodbutton(){
        if(!foodnameTextfield.getText().isEmpty()) {
            FoodDatabaseOperations.deleteFoods(foodnameTextfield.getText());
            choiceBoxDataCreate();
        }
    }

    /**
     * onAction event of addFoodToMyfoodbutton
     * and this add a {@Foods} from foodsChoiceBox to myfoodLists.
     */
    public void addFoodToMyfoodbutton() {
        if (foodsChoiceBox.getValue() != null) {
            myfoodLists.getItems().add(foodsChoiceBox.getValue());
            countingFoods.add(FoodDatabaseOperations.allFoods().get(foodsChoiceBox.getSelectionModel().getSelectedIndex()));
            CalorieNumberLabel.setText(Integer.toString(CalorieCounting.sum(countingFoods))+" kcal");
            differenceObserver();
        }
    }

    /**
     * onAction event of removefoodButton
     * and remove a food from myfoodLists.
     */
    public void removefoodButton() {
        if(!myfoodLists.getItems().isEmpty()&&myfoodLists.getSelectionModel().getSelectedIndex()!=-1) {
            countingFoods.remove(myfoodLists.getSelectionModel().getSelectedIndex());
            myfoodLists.getItems().remove(myfoodLists.getSelectionModel().getSelectedIndex());
            CalorieNumberLabel.setText((CalorieCounting.sum(countingFoods)) + " kcal");
            differenceObserver();
        }
    }

    /**
     * Set the text in profileKcal label.
     * @param sex sex of profile.
     */

    public void profileKcalsetText(String sex){
        if(!weightTextfield.getText().isEmpty()) {
            profileKcal.setText(Integer.toString(CalorieCounting.maxCalorieIntake(sex, Integer.parseInt(weightTextfield.getText()))));
            differenceObserver();
        }
    }

    /**
     * onAction event of manButton.
     */
    public void manRadiobuttonEvent(){
        profileKcalsetText(manButton.getText());
    }
    /**
     * onAction event of womanButton.
     */
    public void womanRadiobuttonEvent(){
        profileKcalsetText(womanButton.getText());
    }

    /**
     * Watch the weightTextfield changes.
     */
    public void profileKcalcounting(){
        weightTextfield.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
               if(manButton.isSelected())
                   manRadiobuttonEvent();
               else
                   womanRadiobuttonEvent();
                }

        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ProfilNameLabel.setText(profileLoginController.currentProfil);

        ToggleGroup group = new ToggleGroup();
        manButton.setToggleGroup(group);
        womanButton.setToggleGroup(group);
        manButton.setSelected(true);

        profileKcalcounting();

        choiceBoxDataCreate();

    }

}
