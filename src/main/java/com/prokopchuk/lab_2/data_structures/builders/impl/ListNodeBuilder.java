package com.prokopchuk.lab_2.data_structures.builders.impl;

import com.prokopchuk.lab_2.data_structures.builders.interfaces.IListNodeBuilder;
import com.prokopchuk.lab_2.data_structures.nodes.ListNode;

public class ListNodeBuilder<T> implements IListNodeBuilder<T, ListNode<T>, ListNodeBuilder<T>> {
    private BaseBuilder<T, ListNode<T>> builder;

    public ListNodeBuilder(){
        this.builder = new BaseBuilder<T, ListNode<T>>();
        builder.setInstance(new ListNode<T>());
    }

    @Override
    public ListNodeBuilder<T> setValue(T value) {
        this.builder.setValue(value);
        return this;
    }

    @Override
    public ListNodeBuilder<T> setNext(ListNode<T> next) {
        this.builder.build().setNext(next);
        return this;
    }

    @Override
    public ListNode<T> build() {
        return this.builder.build();
    }

    @Override
    public void setInstance(ListNode<T> node) {
        this.builder.setInstance(node);
    }
}
