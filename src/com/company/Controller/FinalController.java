package com.company.Controller;

import com.company.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class FinalController {
    @FXML
    public void openAboutTab(ActionEvent event) {
        AboutPage aboutPage = new AboutPage();
        Stage newWindow = aboutPage.aboutDisplay();
        newWindow.setX(500);
        newWindow.setY(500);
        newWindow.setTitle("About");
        newWindow.show();
    }
    @FXML
    public void openHelpTab(ActionEvent event) {
        HelpPage aboutPage = new HelpPage();
        Stage newWindow = aboutPage.helpDisplay();
        newWindow.setX(500);
        newWindow.setY(500);
        newWindow.setTitle("About");
        newWindow.show();
    }
    @FXML
    public void quitApp(){
        Platform.exit();
        System.exit(0);
    }

    public void newDiagram(ActionEvent event) throws Exception {
        Main main = new Main();
        Stage primaryStage = new Stage();
        main.start(primaryStage);
    }
}
