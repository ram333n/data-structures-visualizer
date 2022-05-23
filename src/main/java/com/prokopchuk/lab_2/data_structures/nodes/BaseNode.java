package com.prokopchuk.lab_2.data_structures.nodes;

public class BaseNode<T> {
    public BaseNode(){}

    public BaseNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
    protected T value;
}