package com.prokopchuk.lab_2.data_structures.impl;

import com.prokopchuk.lab_2.data_structures.factories.DataStructuresFactory;

import java.util.Iterator;

public class UniqueDataStructure<T extends Comparable<T>> implements Iterable<T> {
    private DataStructure<T> data;

    public UniqueDataStructure(StructureType type) {
        data = DataStructuresFactory.getInstance().<T>create(type);
    }

    public void insert(T value) {
        if(search(value)) {
            return;
        }

        data.insert(value);
    }

    public boolean search(T value) {
        return data.search(value);
    }

    public boolean delete(T value) {
        return data.delete(value);
    }

    @Override
    public Iterator<T> iterator() {
        return data.iterator();
    }
}
