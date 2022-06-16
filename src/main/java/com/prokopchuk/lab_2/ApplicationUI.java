package com.prokopchuk.lab_2;

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

    @FXML
    void initialize() {
        dataStructureComboBox.setItems(FXCollections.observableArrayList("Linked list", "Binary search tree", "Red-black tree"));
    }

}