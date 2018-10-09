package com.company.Controller;

import com.company.Model.Activity;
import com.company.Model.Business;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeController {
    @FXML                                                       //getting textFields data from user inputs in local variables.
    private TextField activityName;
    @FXML
    private TextField duration;
    @FXML
    private TextField dependency;
    @FXML
    private Label durationLabel;
    @FXML
    private Label activityLabel;
    @FXML
    private Label dependencyLabel;
    @FXML
    private TableColumn activityColumn;
    @FXML
    private TableColumn durationColumn;
    @FXML
    private TableColumn dependencyColumn;
    @FXML
    private TableColumn editOrDelete;
    @FXML
    private Label errorActivity;
    @FXML
    private Label errorDuration;
    @FXML
    private Label errorDependency;
    /*@FXML
    private ComboBox dependencyMenu;*/

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
        HelpPage aboutPage = new HelpPage();
        Stage newWindow = aboutPage.helpDisplay();
        newWindow.setX(500);
        newWindow.setY(500);
        newWindow.setTitle("Help");
        newWindow.show();
    }

    @FXML                                                       //method to quit the application on button click.
    public void quitApp(){
        Platform.exit();
        System.exit(0);
    }

    /*private ArrayList<Integer> times = new ArrayList();                 //local variables to store the user data.
    private ArrayList<String> inputActivity = new ArrayList();
    private  ArrayList<String> inputDdependency = new ArrayList();*/

    private List<Activity> activityList = new ArrayList<Activity>();
    private List<Map.Entry<List<String>,Integer>> Output= new ArrayList<Map.Entry<List<String>,Integer>> ();

    //private HashMap<String,String> activites = new HashMap<>();

    @FXML                                                       //method to add the activity entered by user to our local variables
    public void addActivity(ActionEvent event) {
        activityName.getStyleClass().remove("error");       //removing the error styling of red textfields
        duration.getStyleClass().remove("error");
        dependency.getStyleClass().remove("error");
        errorActivity.setText("");                              //setting the page to reset after each activity is added.
        errorDuration.setText("");
        errorDependency.setText("");

        /*if(inputDdependency.isEmpty()){                                //Setting up first activity as a root element.
            dependency.setText("root");
            errorDependency.setText("This is a root Activity");
            errorDependency.setStyle("-fx-text-fill: black;");*/

        if(dependency.getText().isEmpty()){                                //Setting up first activity as a root element.
            dependency.setText("root");
            errorDependency.setText("This is a root Activity");
            errorDependency.setStyle("-fx-text-fill: black;");

        }
        //Error handling of input fields
        if(activityName.getText().isEmpty()||duration.getText().isEmpty()||dependency.getText().isEmpty()|| !duration.getText().matches("\\d*")){
            if(activityName.getText().isEmpty()) {
                activityName.getStyleClass().add("error");
                errorActivity.setText("Please enter Activity Name");
            }
            if(duration.getText().isEmpty()) {
                duration.getStyleClass().add("error");
                errorDuration.setText("Please enter Duration");
            }
            if(dependency.getText().isEmpty()) {
                dependency.getStyleClass().add("error");
                errorDependency.setText("Please enter Dependency");
            }
            if(!duration.getText().matches("\\d*")){
                duration.getStyleClass().add("error");
                errorDuration.setText("Please enter only numeric values");
            }
            return;
        }

        //to check that the dependencies are previous entries.

        else{
            /*Activity activity = new Activity(activityName.getText(),Integer.parseInt(duration.getText()),dependency.getText());*/
            //activites.put(activity.getActivity(),activity.getDependency());
            /*inputActivity.add(activity.getActivity());
            inputDdependency.add(activity.getDependency());
            times.add(activity.getDuration());*/
            Activity activity = new Activity();
            activity.setActivity(activityName.getText());
            activity.setDuration(Integer.parseInt(duration.getText()));
            activity.setDependency(dependency.getText());
            activityList.add(activity);
            //dependencyMenu.getItems().addAll(activity.getActivity());
        }
        /*System.out.println(times);
        System.out.println(inputActivity);
        System.out.println(inputDdependency);*/
        //dependency.setDisable(false);
        resetActivity(event);
    }

    @FXML
    public void resetActivity(ActionEvent event) {
        activityName.clear();
        errorActivity.setText("");
        activityName.getStyleClass().remove("error");
        duration.clear();
        errorDuration.setText("");
        duration.getStyleClass().remove("error");
        dependency.clear();
        errorDependency.setText("");
        dependency.getStyleClass().remove("error");
    }

    @FXML
    public void createNetworkDiagram(ActionEvent event) throws Exception {
        FinalPage window = new FinalPage();
        Stage newWin = window.finalPage();
        newWin.setX(300);
        newWin.setY(100);
        newWin.show();
        Business path = new Business();
        Output = path.createNetwork(activityList);
    }

    /*public void chooseDependency(ActionEvent event) {
        dependency.setText(dependencyMenu.getValue().toString());
    }*/
}