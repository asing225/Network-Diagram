package com.company.Controller;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AboutPage {
    public Stage aboutDisplay(){
        AboutText aboutText = new AboutText();
        Text text = aboutText.aboutText();
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(text);
        Scene secondScene = new Scene(secondaryLayout, 500, 700);
        Stage newWindow = new Stage();
        newWindow.setScene(secondScene);
        return newWindow;
    }
}
