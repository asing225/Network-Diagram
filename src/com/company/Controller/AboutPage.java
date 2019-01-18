package com.company.Controller;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AboutPage {
    public Stage aboutDisplay(){
        AboutText aboutText = new AboutText();
        Text[] text = aboutText.aboutText();
        StackPane secondaryLayout = new StackPane();
        Text about = new Text();
        about.setText(text[0].getText().toString()+text[1].getText().toString()+text[2].getText().toString()+text[3].getText().toString());
        secondaryLayout.getChildren().add(about);
        Scene secondScene = new Scene(secondaryLayout, 500, 700);
        Stage newWindow = new Stage();
        newWindow.setScene(secondScene);
        return newWindow;
    }
}