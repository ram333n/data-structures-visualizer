package com.prokopchuk.lab_2.ui.commands;

import com.prokopchuk.lab_2.data_structures.impl.DataStructure;
import javafx.scene.control.Alert;

public class CommandDelete<T> implements ICommand<T> {
    @Override
    public void execute(DataStructure<T> structure, T value) {
        if(!structure.delete(value)) {
            Alert message = new Alert(Alert.AlertType.WARNING);
            message.setContentText("Value  " + value.toString() + " hasn't found" );
            message.showAndWait();
            return;
        }
    }
}
