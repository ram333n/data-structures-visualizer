package com.prokopchuk.lab_2.ui.commands;

import com.prokopchuk.lab_2.data_structures.impl.DataStructure;
import com.prokopchuk.lab_2.ui.NotFoundException;
import javafx.scene.canvas.Canvas;

public interface ICommand<T> {
    void execute(DataStructure<T> structure, T value) throws NotFoundException;
}
