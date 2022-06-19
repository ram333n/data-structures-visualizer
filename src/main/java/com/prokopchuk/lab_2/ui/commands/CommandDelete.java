package com.prokopchuk.lab_2.ui.commands;

import com.prokopchuk.lab_2.data_structures.impl.DataStructure;
import com.prokopchuk.lab_2.ui.NotFoundException;
import javafx.scene.control.Alert;

public class CommandDelete<T> implements ICommand<T> {
    @Override
    public void execute(DataStructure<T> structure, T value) throws NotFoundException {
        if(!structure.delete(value)) {
            throw new NotFoundException("Value " + value.toString() + " hasn't found");
        }
    }
}
