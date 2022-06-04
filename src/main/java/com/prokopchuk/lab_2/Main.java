package com.prokopchuk.lab_2;

import com.prokopchuk.lab_2.data_structures.builders.impl.ListNodeBuilder;
import com.prokopchuk.lab_2.data_structures.builders.impl.RBTreeNodeBuilder;
import com.prokopchuk.lab_2.data_structures.nodes.BaseNode;
import com.prokopchuk.lab_2.data_structures.nodes.Color;
import com.prokopchuk.lab_2.data_structures.nodes.ListNode;
import com.prokopchuk.lab_2.data_structures.nodes.RBTreeNode;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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
        RBTreeNode<String> affa = new RBTreeNodeBuilder<String>().setValue("hui").setColor(Color.BLACK).setParent(null).setLeft(null).setRight(null).build();
        ListNode<Integer> fbbf = new ListNodeBuilder<Integer>().setValue(42).setNext(null).build();
        List<BaseNode> l = new LinkedList();
        l.add(affa);
        l.add(fbbf);

        for(BaseNode node : l) {
            System.out.println(node.getValue());
            if(node instanceof RBTreeNode<?>) {
                System.out.println(((RBTreeNode<?>) node).getColor());
            }
        }

    }
}