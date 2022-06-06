package com.prokopchuk.lab_2.data_structures.iterators;

import com.prokopchuk.lab_2.data_structures.nodes.ListNode;

import java.util.Iterator;

public class ListIterator<T> implements Iterator<T> {
    private ListNode<T> node;

    public ListIterator(ListNode<T> node) {
        this.node = node;
    }

    @Override
    public boolean hasNext() {
        return node != null;
    }

    @Override
    public T next() {
        if(!hasNext()) {
            return null;
        }

        T result = node.getValue();
        node = node.getNext();

        return result;
    }
}
