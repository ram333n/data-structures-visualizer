package com.prokopchuk.lab_2.data_structures.impl;

import java.util.Iterator;

public interface DataStructure<T> extends Iterable<T> {
    void insert(T value);
    boolean search(T value);
    boolean delete(T value);
}
