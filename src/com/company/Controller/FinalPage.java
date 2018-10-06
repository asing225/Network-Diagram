package com.company.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class FinalPage {
    public Stage finalPage() throws Exception {
        Pane pane = FXMLLoader.load(this.getClass().getResource("../View/Final.fxml"));
        Stage window = new Stage();
        window.setTitle("Network Diagram Output");
        Scene scene = new Scene(pane, 1440, 900);
        window.setScene(scene);
        return window;
    }
}
