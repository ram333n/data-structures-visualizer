package com.prokopchuk.lab_2;

import com.prokopchuk.lab_2.data_structures.impl.BSTree;
import com.prokopchuk.lab_2.data_structures.impl.DataStructure;
import com.prokopchuk.lab_2.data_structures.impl.RBTree;
import com.prokopchuk.lab_2.data_structures.impl.SinglyLinkedList;
import com.prokopchuk.lab_2.ui.CanvasPrinter;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;

public class ApplicationUI {
    @FXML
    private ComboBox<String> dataStructureComboBox;

    @FXML
    private RadioButton insertRadioBtn;

    @FXML
    private RadioButton removeRadioBtn;

    @FXML
    private TextField inputField;

    @FXML
    private Button applyBtn;

    @FXML
    private Button undoBtn;

    @FXML
    private Button redoBtn;

    @FXML
    private Canvas canvasField;



    private DataStructure<Integer> structure;
    private CanvasPrinter<Integer> canvasPrinter;
    private boolean toInsert = true;
    Alert message;

    @FXML
    void initialize() {
        dataStructureComboBox.setItems(FXCollections.observableArrayList("Linked list", "Binary search tree", "Red-black tree"));
        canvasPrinter = new CanvasPrinter(canvasField);

        dataStructureComboBox.setOnAction(actionEvent -> {
            setStructure(dataStructureComboBox.getValue());
            //TODO : clear canvas
        });

        insertRadioBtn.setOnAction(actionEvent -> {
            toInsert = true;
        });

        removeRadioBtn.setOnAction(actionEvent -> {
            toInsert = false;
        });

        structure = new RBTree<>();
        for(Integer i = 0; i < 14; ++i) {
            structure.insert(i);
        }

        structure.insert(-532);
        canvasPrinter.setStructure(structure);

    }

    private void setStructure(String type) {
        if(type.equals("Linked list")) {
            structure = new SinglyLinkedList<>();
        } else if(type.equals("Binary search tree")) {
            structure = new BSTree<>();
        } else if(type.equals("Red-black tree")) {
            structure = new RBTree<>();
        } else {
            throw new UnsupportedOperationException("Not implemented yet");
        }
    }

}