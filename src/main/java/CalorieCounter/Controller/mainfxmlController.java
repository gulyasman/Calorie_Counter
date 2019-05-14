package CalorieCounter.Controller;

import CalorieCounter.Main;
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
import javafx.scene.layout.Background;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

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

    public void differenceObserver(){
        if(!weightTextfield.getText().isEmpty()){
        if(CalorieCounting.sum(countingFoods)<=Integer.parseInt(profileKcal.getText())){
            CalorieNumberLabel.setStyle("-fx-text-fill: green;-fx-background-color: grey");
            calorieDifferenceLabel.setStyle("-fx-text-fill: green;-fx-background-color: grey");
           // CalorieNumberLabel.setStyle("-fx-background-color: grey");
        }
        else {
            CalorieNumberLabel.setStyle("-fx-text-fill: red;-fx-background-color: grey");
            calorieDifferenceLabel.setStyle("-fx-text-fill: red;-fx-background-color: grey");
            //CalorieNumberLabel.setStyle("-fx-background-color: grey");
        }
        calorieDifferenceLabel.setText(Integer.toString(CalorieCounting.differenceCounter(CalorieCounting.sum(countingFoods), Integer.parseInt(profileKcal.getText()))));
        }
    }


    public void choiceBoxDataCreate(){
        foodsChoiceBox.getItems().clear();
        for (Foods i: FoodDatabaseOperations.allFoods()) {
            foodsChoiceBox.getItems().add(i);
        }
        foodnameTextfield.setText("");
        kcalTextfield.setText("");
    }

    public void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/profileLogin.fxml"));
        Parent root = loader.load();
        Main.CurrentStage.setScene(new Scene(root));
    }
   /* public void choiceBoxevent(ActionEvent event){
        //System.out.println( foodsChoiceBox.getSelectionModel().getSelectedIndex());
        /*System.out.println(FoodDatabaseOperations.allFoods().get(foodsChoiceBox.getSelectionModel().getSelectedIndex()));
        System.out.println(foodnameTextfield.getText());
    }*/

    public void addFoodbutton(){
        if(!foodnameTextfield.getText().isEmpty()&&!kcalTextfield.getText().isEmpty()) {
            FoodDatabaseOperations.addFoods(foodnameTextfield.getText(), Integer.parseInt(kcalTextfield.getText()));
            choiceBoxDataCreate();
        }
    }
    public void deleteFoodbutton(){
        if(!foodnameTextfield.getText().isEmpty()) {
            FoodDatabaseOperations.deleteFoods(foodnameTextfield.getText());
            choiceBoxDataCreate();
        }
    }

    public void addFoodToMyfoodbutton() {
        if (foodsChoiceBox.getValue() != null) {
            myfoodLists.getItems().add(foodsChoiceBox.getValue());
            countingFoods.add(FoodDatabaseOperations.allFoods().get(foodsChoiceBox.getSelectionModel().getSelectedIndex()));
            CalorieNumberLabel.setText(Integer.toString(CalorieCounting.sum(countingFoods))+" kcal");
            differenceObserver();
        }
    }

    public void removefoodButton() {
        if(!myfoodLists.getItems().isEmpty()&&myfoodLists.getSelectionModel().getSelectedIndex()!=-1) {
            countingFoods.remove(myfoodLists.getSelectionModel().getSelectedIndex());
            myfoodLists.getItems().remove(myfoodLists.getSelectionModel().getSelectedIndex());
            CalorieNumberLabel.setText(Integer.toString(CalorieCounting.sum(countingFoods)) + " kcal");
            differenceObserver();
        }
    }

    public void profileKcalsetText(String sex){
        if(!weightTextfield.getText().isEmpty()) {
            profileKcal.setText(Integer.toString(CalorieCounting.maxCalorieIntake(sex, Integer.parseInt(weightTextfield.getText()))));
            differenceObserver();
        }
    }

    public void manRadiobuttonEvent(){
        profileKcalsetText(manButton.getText());
    }
    public void womanRadiobuttonEvent(){
        profileKcalsetText(womanButton.getText());
    }

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
