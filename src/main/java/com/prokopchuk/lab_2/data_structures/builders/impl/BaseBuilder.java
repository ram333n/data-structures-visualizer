package com.prokopchuk.lab_2.data_structures.builders.impl;

import com.prokopchuk.lab_2.data_structures.builders.interfaces.IBaseBuilder;
import com.prokopchuk.lab_2.data_structures.nodes.BaseNode;

public class BaseBuilder<T, Node extends BaseNode<T>> implements IBaseBuilder<T, Node, BaseBuilder<T,Node>> {
    protected Node toBuild;

    @Override
    public BaseBuilder<T, Node> setValue(T value) {
        this.toBuild.setValue(value);
        return this;
    }

    @Override
    public Node build() {
        return this.toBuild;
    }

    @Override
    public void setInstance(Node node) {
        this.toBuild = node;
    }
}
