package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane pane = FXMLLoader.load(this.getClass().getResource("View/Home.fxml"));
        primaryStage.setTitle("Network Diagram Analyzer");
        Scene scene = new Scene(pane, 1440, 900);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(
                String.valueOf(getClass().getClassLoader().getResource("com/company/style.css")));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}