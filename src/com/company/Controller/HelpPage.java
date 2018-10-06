package com.company.Controller;

import com.company.Controller.HelpText;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelpPage {
    public Stage helpDisplay(){
        HelpText helpText = new HelpText();
        Text text = helpText.helpText();
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(text);
        Scene secondScene = new Scene(secondaryLayout, 500, 700);
        Stage newWindow = new Stage();
        newWindow.setScene(secondScene);
        return newWindow;
    }
}
