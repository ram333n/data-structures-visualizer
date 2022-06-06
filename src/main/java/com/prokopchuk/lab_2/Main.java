package com.prokopchuk.lab_2;

import com.prokopchuk.lab_2.data_structures.impl.BSTree;
import com.prokopchuk.lab_2.data_structures.impl.LinkedList;
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
        //launch();
        BSTree<Integer> l = new BSTree<Integer>();
        for(int i = 0; i < 10; ++i) {
            l.insert(i);
        }

        for(Integer i : l) {
            System.out.print(i + " ");
        }

        if(l.delete(5)){
            System.out.println("Good");
        }

        if(l.delete(4) && l.delete(3) && l.delete(1)) {
            for(Integer i : l) {
                System.out.print(i + " ");
            }
        }
        System.out.println("End");
    }
}