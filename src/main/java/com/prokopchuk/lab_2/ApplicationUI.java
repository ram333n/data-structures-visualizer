package com.prokopchuk.lab_2;

import com.prokopchuk.lab_2.data_structures.impl.BSTree;
import com.prokopchuk.lab_2.data_structures.impl.DataStructure;
import com.prokopchuk.lab_2.data_structures.impl.RBTree;
import com.prokopchuk.lab_2.data_structures.impl.SinglyLinkedList;
import com.prokopchuk.lab_2.ui.CanvasPrinter;
import com.prokopchuk.lab_2.ui.NotFoundException;
import com.prokopchuk.lab_2.ui.commands.CommandDelete;
import com.prokopchuk.lab_2.ui.commands.CommandInsert;
import com.prokopchuk.lab_2.ui.commands.ICommand;
import com.prokopchuk.lab_2.ui.memento.Snapshot;
import com.prokopchuk.lab_2.ui.memento.SnapshotManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;

import java.util.LinkedList;

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

    private DataStructure<Integer> structure = new SinglyLinkedList<>();
    private CanvasPrinter<Integer> canvasPrinter;
    private ICommand<Integer> command = new CommandInsert<>();
    private SnapshotManager<Integer> snapshotManager = new SnapshotManager<>();

    @FXML
    void initialize() {
        dataStructureComboBox.setItems(FXCollections.observableArrayList("Singly linked list", "Binary search tree", "Red-black tree"));
        dataStructureComboBox.getSelectionModel().select(0);

        canvasPrinter = new CanvasPrinter(canvasField);

        snapshotManager.addSnapshot(new Snapshot<>());

        dataStructureComboBox.setOnAction(actionEvent -> {
            setStructure(dataStructureComboBox.getValue());
            snapshotManager.clear();
            clearCanvas(canvasField);
            snapshotManager.addSnapshot(new Snapshot<>());
        });

        insertRadioBtn.setOnAction(actionEvent -> {
            command = new CommandInsert<>();
        });

        removeRadioBtn.setOnAction(actionEvent -> {
            command = new CommandDelete<>();
        });

        applyBtn.setOnAction(actionEvent -> {
            try {
                Integer value = Integer.parseInt(inputField.getText());
                command.execute(structure, value);
                clearCanvas(canvasField);
                canvasPrinter.setStructure(structure);
                snapshotManager.addSnapshot(canvasPrinter.getSnapshot());
            } catch (Exception ex) {
                Alert message = new Alert(Alert.AlertType.WARNING);
                message.setContentText(ex instanceof NotFoundException ? ex.getMessage() : "Incorrect input");
                message.showAndWait();
            }
            inputField.clear();
        });

        undoBtn.setOnAction(actionEvent -> {
            Snapshot<Integer> snapshot = snapshotManager.undo();

            if(snapshot == null) {
                Alert message = new Alert(Alert.AlertType.WARNING);
                message.setContentText("There are no previous versions of structure");
                message.showAndWait();
            } else {
                restore(snapshot);
            }
        });

        redoBtn.setOnAction(actionEvent -> {
            Snapshot<Integer> snapshot = snapshotManager.redo();

            if(snapshot == null) {
                Alert message = new Alert(Alert.AlertType.WARNING);
                message.setContentText("There are no versions of structure after that");
                message.showAndWait();
            } else {
                restore(snapshot);
            }
        });
    }

    private void setStructure(String type) {
        if(type.equals("Singly linked list")) {
            structure = new SinglyLinkedList<>();
        } else if(type.equals("Binary search tree")) {
            structure = new BSTree<>();
        } else if(type.equals("Red-black tree")) {
            structure = new RBTree<>();
        } else {
            throw new UnsupportedOperationException("Not implemented yet");
        }
    }

    private void clearCanvas(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void restore(Snapshot<Integer> snapshot) {
        clearCanvas(canvasField);
        canvasPrinter.restoreBySnapshot(snapshot);
    }
}