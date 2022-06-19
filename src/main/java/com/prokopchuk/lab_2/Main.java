package com.prokopchuk.lab_2;

import com.prokopchuk.lab_2.data_structures.impl.RBTree;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("application_ui.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 800);
        stage.setResizable(false);
        stage.setTitle("Data visualizer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}