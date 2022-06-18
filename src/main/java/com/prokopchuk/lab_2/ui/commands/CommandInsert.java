package com.prokopchuk.lab_2.ui.commands;

import com.prokopchuk.lab_2.data_structures.impl.DataStructure;

public class CommandInsert<T> implements ICommand<T> {
    @Override
    public void execute(DataStructure<T> structure, T value) {
        structure.insert(value);
    }
}
