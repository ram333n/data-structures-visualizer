package com.prokopchuk.lab_2.data_structures.builders.interfaces;

import com.prokopchuk.lab_2.data_structures.nodes.BaseNode;

public interface IBaseBuilder<T, Node extends BaseNode<T>, B> {
    B setValue(T value);
    Node build();
    void setInstance(Node node);
}
