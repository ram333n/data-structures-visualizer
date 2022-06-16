package com.prokopchuk.lab_2.data_structures.impl;

import com.prokopchuk.lab_2.ui.visitors.IVisitor;

public interface DataStructure<T> extends Iterable<T> {
    void insert(T value);
    boolean search(T value);
    boolean delete(T value);
    int getLength();
    void visit(IVisitor<T> visitor);
}
