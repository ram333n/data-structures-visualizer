package com.prokopchuk.lab_2.data_structures.builders.impl;

import com.prokopchuk.lab_2.data_structures.builders.interfaces.IRBTreeNodeBuilder;
import com.prokopchuk.lab_2.data_structures.nodes.Color;
import com.prokopchuk.lab_2.data_structures.nodes.RBTreeNode;

public class RBTreeNodeBuilder<T> implements IRBTreeNodeBuilder<T, RBTreeNode<T>, RBTreeNodeBuilder<T>> {
    private BaseBuilder<T, RBTreeNode<T>> builder;

    public RBTreeNodeBuilder() {
        this.builder = new BaseBuilder<T, RBTreeNode<T>>();
        this.setInstance(new RBTreeNode<T>());
    }

    @Override
    public RBTreeNodeBuilder<T> setValue(T value) {
        this.builder.setValue(value);
        return this;
    }

    @Override
    public RBTreeNode<T> build() {
        return this.builder.build();
    }

    @Override
    public void setInstance(RBTreeNode<T> node) {
        this.builder.setInstance(node);
    }

    @Override
    public RBTreeNodeBuilder<T> setLeft(RBTreeNode<T> left) {
        this.builder.build().setLeft(left);
        return this;
    }

    @Override
    public RBTreeNodeBuilder<T> setRight(RBTreeNode<T> right) {
        this.builder.build().setRight(right);
        return this;
    }

    @Override
    public RBTreeNodeBuilder<T> setParent(RBTreeNode<T> parent) {
        this.builder.build().setParent(parent);
        return this;
    }

    @Override
    public RBTreeNodeBuilder<T> setColor(Color color) {
        this.builder.build().setColor(color);
        return this;
    }
}
