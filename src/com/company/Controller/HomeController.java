package com.company.Controller;


import com.company.Main;
import com.company.Model.Activity;
import com.company.Model.Business;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.List;
import java.util.Map;


public class HomeController extends Control implements Initializable {

    //getting textFields data from user inputs in local variables.
    @FXML
    public TextArea finalOutput;
    @FXML
    private TextField activityName;
    @FXML
    private TextField duration;
    @FXML
    private Label errorActivity;
    @FXML
    private Label errorDuration;
    @FXML
    private Label errorDependency;
    @FXML
    private CheckComboBox<String> dropDown = new CheckComboBox<String>();

    //local variables to store the user data.
    private ArrayList<String> inputActivity = new ArrayList();

    private List<Activity> activityList = new ArrayList<Activity>();
    private List<Map.Entry<List<String>, Integer>> output = new ArrayList<Map.Entry<List<String>, Integer>>();

    @FXML
    //method to add the activity entered by user to our local variables
    public void addActivity(ActionEvent event) {
        activityName.getStyleClass().remove("error");       //removing the error styling of red textfields
        duration.getStyleClass().remove("error");
        dropDown.getStyleClass().remove("error");
        errorActivity.setText("");                              //setting the page to reset after each activity is added.
        errorDuration.setText("");
        errorDependency.setText("");

        //Error handling of input fields
        if (activityName.getText().isEmpty() || duration.getText().isEmpty() || duration.getText().equals("0") || !duration.getText().matches("\\d*") || dropDown.getCheckModel().isEmpty()) {
            if (activityName.getText().isEmpty()) {
                activityName.getStyleClass().add("error");
                errorActivity.setText("Please enter Activity Name");
            }
            if (duration.getText().isEmpty()) {
                duration.getStyleClass().add("error");
                errorDuration.setText("Please enter Duration");
            }
            if (!duration.getText().matches("\\d*")) {
                duration.getStyleClass().add("error");
                errorDuration.setText("Please enter only numeric values");
            }
            if (duration.getText().equals("0")) {
                duration.getStyleClass().add("error");
                errorDuration.setText("Duration of an activity cannot be 0");
            }
            if (dropDown.getCheckModel().isEmpty()) {
                errorDependency.setText("Please select a dependency");
                dropDown.getStyleClass().add("error");
            }
            return;
        } else {

            inputActivity.add(activityName.getText());
            Activity activity = new Activity();
            activity.setActivity(activityName.getText());
            activity.setDuration(Integer.parseInt(duration.getText()));
            activity.setDependency((String.valueOf(dropDown.getCheckModel().getCheckedItems())).replaceAll("\\[","").replaceAll("\\]",""));
            activityList.add(activity);
            finalOutput.setText("Activity "+ activityName.getText() + " added with Duration: "+ duration.getText());
            finalOutput.appendText("\n");
            resetActivity(event);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dropDown.getItems().add(String.valueOf("Root"));
    }

    @FXML
    public void resetActivity(ActionEvent event) {
        activityName.clear();
        errorActivity.setText("");
        activityName.getStyleClass().remove("error");
        duration.clear();
        errorDuration.setText("");
        duration.getStyleClass().remove("error");
        errorDependency.setText("");
        dropDown.getStyleClass().remove("error");
        if(!activityList.isEmpty()){
            dropDown.getItems().clear();
        }
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll(inputActivity);
        dropDown.getItems().addAll(list);
        dropDown.getCheckModel().clearChecks();
    }

    @FXML                                                       //method to open About window on button click.
    public void openAboutTab(ActionEvent event) {
        AboutPage aboutPage = new AboutPage();
        Stage newWindow = aboutPage.aboutDisplay();
        newWindow.setX(500);
        newWindow.setY(500);
        newWindow.setTitle("About");
        newWindow.show();
    }

    @FXML                                                       //method to open Help window on button click.
    public void openHelpTab(ActionEvent event) {
        HelpPage helpPage = new HelpPage();
        Stage newWindow = helpPage.helpDisplay();
        newWindow.setX(500);
        newWindow.setY(500);
        newWindow.setTitle("Help");
        newWindow.show();
    }

    @FXML                                                       //method to quit the application on button click.
    public void quitApp() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void createNetworkDiagram(ActionEvent event) throws Exception {
        if(activityList.size()==0){
            finalOutput.setText("Please enter at least 1 Activity.");
            return;
        }
        else{
            Business path = new Business();
            output = path.createNetwork(activityList);
            Object[] pathsToArrays = output.toArray();
            finalOutput.setText(null);
            for(int i=0;i<pathsToArrays.length;i++){
                String paths = pathsToArrays[i].toString().replaceAll("\\[","").replaceAll("\\]","").replaceAll(",","->").replaceAll(" ","");
                String pathDuration = paths.substring(paths.lastIndexOf("=")).replaceAll("=","");
                finalOutput.appendText("\n" + "Path: "+ paths.substring(0,paths.indexOf("=")) +"\n" + "Duration: "+ pathDuration+ "\n");
            }
        }
    }

    public void newNetworkDiagram(ActionEvent event) throws Exception {
        Main main = new Main();
        Stage primaryStage = new Stage();
        main.start(primaryStage);
    }
}