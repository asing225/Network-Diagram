package com.company.Controller;

        import com.company.Main;
        import com.company.Model.Activity;
        import com.company.Controller.Business;
        import javafx.application.Platform;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.*;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.scene.control.cell.TextFieldTableCell;
        import javafx.stage.Stage;
        import javafx.util.converter.IntegerStringConverter;

        import java.io.BufferedWriter;
        import java.io.FileWriter;
        import java.io.Writer;
        import java.net.URL;
        import java.text.SimpleDateFormat;
        import java.util.*;


public class HomeController implements Initializable {

    @FXML
    public TableView<Activity> activityTable;
    public TextField reportName;
    @FXML
    public TableView<Activity> activityTable;
    @FXML
    private TextField activityName;
    @FXML
    private TextField duration;
    @FXML
    private TextField dependency;
    @FXML
    private TextArea finalOutput;
    @FXML
    private Label errorActivity;
    @FXML
    private Label errorDuration;
    @FXML
    private Label errorDependency;

    private List<Activity> activityList = new ArrayList<Activity>();
    private List<Map.Entry<List<String>, Integer>> output = new ArrayList<Map.Entry<List<String>, Integer>>();

    private TableColumn<Activity, String> activityNameColumn = new TableColumn<Activity, String>("Activity Name");
    private TableColumn<Activity, Integer> durationColumn = new TableColumn<Activity, Integer>("Duration");
    private TableColumn<Activity, String> dependencyColumn = new TableColumn<Activity, String>("Dependency");

    private TableColumn<Activity, String> activityNameColumn = new TableColumn<Activity, String>("Activity Name");
    private TableColumn<Activity, Integer> durationColumn = new TableColumn<Activity, Integer>("Duration");
    private TableColumn<Activity, String> dependencyColumn = new TableColumn<Activity, String>("Dependency");

    @FXML
    //method to add the activity entered by user to our local variables
    public void addActivity(ActionEvent event) {
        activityName.getStyleClass().remove("error");       //removing the error styling of red textfields
        duration.getStyleClass().remove("error");
        dependency.getStyleClass().remove("error");
        errorActivity.setText("");                              //setting the page to reset after each activity is added.
        errorDuration.setText("");
        errorDependency.setText("");

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
        } else {

            Activity activity = new Activity();
            activity.setActivity(activityName.getText());
            activity.setDuration(Integer.parseInt(duration.getText()));
            activity.setDependency(dependency.getText());
            finalOutput.setText("Activity "+ activityName.getText() + " added with Duration: "+ duration.getText());
            finalOutput.appendText("\n");
            activityList.add(activity);

            ObservableList<Activity> data = FXCollections.observableArrayList(activityList);
            activityTable.setItems(data);
        }
        resetActivity(event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        activityTable.setEditable(true);
        activityNameColumn.setCellValueFactory(new PropertyValueFactory("activity"));
        activityNameColumn.setMinWidth(250);
        activityNameColumn.setCellFactory(TextFieldTableCell.<Activity>forTableColumn());
        editableActivityColumn();

        durationColumn.setCellValueFactory(new PropertyValueFactory("duration"));
        durationColumn.setMinWidth(250);
        durationColumn.setCellFactory(TextFieldTableCell.<Activity, Integer>forTableColumn(new IntegerStringConverter()));
        editableDurationColumn();

        dependencyColumn.setCellValueFactory(new PropertyValueFactory("dependency"));
        dependencyColumn.setMinWidth(250);
        dependencyColumn.setCellFactory(TextFieldTableCell.<Activity>forTableColumn());
        editableDependencyColumn();

        activityTable.getColumns().addAll(activityNameColumn, durationColumn, dependencyColumn);
    }

    private void editableActivityColumn(){
        activityNameColumn.setOnEditCommit((TableColumn.CellEditEvent<Activity, String> event) -> {
            final String name = event.getNewValue() != null ? event.getNewValue()
                    : event.getOldValue();
            ((Activity) event.getTableView().getItems().get(event.getTablePosition().getRow())).setActivity(name);
            activityTable.refresh();
        });
    }

    private void editableDurationColumn(){
        durationColumn.setOnEditCommit((TableColumn.CellEditEvent<Activity, Integer> event) -> {
            final int time = event.getNewValue() != null ? event.getNewValue()
                    : event.getOldValue();
            ((Activity) event.getTableView().getItems().get(event.getTablePosition().getRow())).setDuration(time);
            activityTable.refresh();
        });
    }

    private void editableDependencyColumn(){
        dependencyColumn.setOnEditCommit((TableColumn.CellEditEvent<Activity, String> event) -> {
            final String depend = event.getNewValue() != null ? event.getNewValue()
                    : event.getOldValue();
            ((Activity) event.getTableView().getItems().get(event.getTablePosition().getRow())).setDependency(depend);
            activityTable.refresh();
        });
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
            if(output==null){
                finalOutput.setText("The Network Diagram should not be cyclic or there should not be any disjointed path.");
            }
            else{
                Object[] pathsToArrays = output.toArray();
                finalOutput.setText("Displaying All Path/s in the network diagram: \n");

                for(int i=0;i<pathsToArrays.length;i++){
                    String paths = pathsToArrays[i].toString().replaceAll("\\[","").replaceAll("\\]","").replaceAll(",","->").replaceAll(" ","");
                    String pathDuration = paths.substring(paths.lastIndexOf("=")).replaceAll("=","");
                    finalOutput.appendText("\n" + "Path: "+ paths.substring(0,paths.indexOf("=")) +"\n" + "Duration: "+ pathDuration+ "\n");
                }
            }
        }
    }

    public void newNetworkDiagram(ActionEvent event) throws Exception {
        Main main = new Main();
        Stage primaryStage = new Stage();
        main.start(primaryStage);
    }

    public void updateDiagram(ActionEvent event) throws Exception {
        createNetworkDiagram(event);

    }

    public void createReport(ActionEvent event) throws Exception{
        List<Activity> sortedActivityList = sortedList(activityList);
        String file = "/Users/amanjotsingh/Downloads/" + reportName.getText()+ ".txt";
        Writer writer = null;
        try{
            writer = new BufferedWriter(new FileWriter(file));
            writer.write("Network Diagram Activity Report\n\n");
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.YYYY 'at' HH:mm:ss");
            writer.write("File created at: " + sdf.format(cal.getTime()));
            writer.write("\n\n");
            for(Activity activity: sortedActivityList){
                String text = "\nActivity name: " + activity.getActivity() +"\n"+ "Duration: "+ activity.getDuration()+ "\n"
                        + "Dependencies: " + activity.getDependency();
                writer.write(text);
            }

            writer.write("\n");
            Object[] pathsToArrays = output.toArray();
            finalOutput.setText(null);
            for(int i=0;i<pathsToArrays.length;i++){
                String paths = pathsToArrays[i].toString().replaceAll("\\[","").replaceAll("\\]","").replaceAll(",","->").replaceAll(" ","");
                String pathDuration = paths.substring(paths.lastIndexOf("=")).replaceAll("=","");
                writer.write("\nThe "+i +"th path is :" + paths.substring(0,paths.indexOf("=")));
                writer.write("\nThe duration of this path is :" + pathDuration + "\n");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            writer.close();
            finalOutput.setText("Activity Report generated.");
        }
    }

    public List<Activity> sortedList(List<Activity> list) {
        int i, j, compare = 0;
        Activity temp = new Activity();
        for (i = 0; i < list.size(); i++) {
            temp = list.get(i);
            for (j = i + 1; j < list.size(); j++) {
                compare = list.get(i).getActivity().compareTo(list.get(j).getActivity());
                if (compare > 0) {
                    list.get(i).setActivity(list.get(j).getActivity());
                    list.get(i).setDependency(list.get(j).getDependency());
                    list.get(i).setDuration(list.get(j).getDuration());

                    list.get(j).setActivity(temp.getActivity());
                    list.get(j).setDuration(temp.getDuration());
                    list.get(j).setDependency(temp.getDependency());
                }
            }
        }
        return list;
    }

    public void displayCriticalPaths(ActionEvent event) {
        if(output==null){
            finalOutput.setText("The Network Diagram should not be cyclic or there should not be any disjointed path.");
        }
        else{
            Object[] pathsToArrays = output.toArray();
            finalOutput.setText("Displaying Critical Path/s in the network diagram: \n");
            String paths = pathsToArrays[0].toString().replaceAll("\\[","").replaceAll("\\]","").replaceAll(",","->").replaceAll(" ","");
            String pathDuration = paths.substring(paths.lastIndexOf("=")).replaceAll("=","");
            finalOutput.appendText("\n" + "Path: "+ paths.substring(0,paths.indexOf("=")) +"\n" + "Duration: "+ pathDuration+ "\n");
            for(int i=1;i<pathsToArrays.length;i++){
                String path = pathsToArrays[i].toString().replaceAll("\\[","").replaceAll("\\]","").replaceAll(",","->").replaceAll(" ","");
                String pathD = path.substring(path.lastIndexOf("=")).replaceAll("=","");
                if(Integer.parseInt(pathD) == Integer.parseInt(pathDuration)){
                    finalOutput.appendText("\n" + "Path: "+ path.substring(0,path.indexOf("=")) +"\n" + "Duration: "+ pathD+ "\n");
                }
            }
        }
    }
}
