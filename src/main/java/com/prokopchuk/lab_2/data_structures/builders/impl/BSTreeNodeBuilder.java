package com.prokopchuk.lab_2.data_structures.builders.impl;

import com.prokopchuk.lab_2.data_structures.builders.interfaces.IBinaryTreeNodeBuilder;
import com.prokopchuk.lab_2.data_structures.nodes.BSTreeNode;

public class BSTreeNodeBuilder<T> implements IBinaryTreeNodeBuilder<T, BSTreeNode<T>, BSTreeNodeBuilder<T>> {
    private BaseBuilder<T, BSTreeNode<T>> builder;

    public BSTreeNodeBuilder() {
        this.builder = new BaseBuilder<T, BSTreeNode<T>>();
        this.setInstance(new BSTreeNode<T>());

    }

    @Override
    public BSTreeNodeBuilder<T>  setValue(T value) {
        this.builder.setValue(value);
        return this;
    }

    @Override
    public BSTreeNode<T> build() {
        return this.builder.build();
    }

    @Override
    public void setInstance(BSTreeNode<T> node) {
        this.builder.setInstance(node);
    }

    @Override
    public BSTreeNodeBuilder<T> setLeft(BSTreeNode<T> left) {
        this.builder.build().setLeft(left);
        return this;
    }

    @Override
    public BSTreeNodeBuilder<T> setRight(BSTreeNode<T> right) {
        this.builder.build().setRight(right);
        return this;
    }

    @Override
    public BSTreeNodeBuilder<T> setParent(BSTreeNode<T> parent) {
        this.builder.build().setParent(parent);
        return this;
    }
}
