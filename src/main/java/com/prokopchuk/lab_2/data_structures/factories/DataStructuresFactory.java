package com.prokopchuk.lab_2.data_structures.factories;

import com.prokopchuk.lab_2.data_structures.impl.*;

public class DataStructuresFactory {
    private static DataStructuresFactory instance;

    public static DataStructuresFactory getInstance() {
        if(instance == null) {
            instance = new DataStructuresFactory();
        }
        return instance;
    }

    public <T extends Comparable<T>> DataStructure<T> create(StructureType type) {
        switch (type) {
            case SINGLY_LINKED_LIST :
                return new SinglyLinkedList<>();
            case BSTREE:
                return new BSTree<>();
            case RBTREE:
                return new RBTree<>();
            default:
                throw new UnsupportedOperationException("Not implemented yet");
        }
    }
}
