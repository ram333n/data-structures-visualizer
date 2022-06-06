package com.prokopchuk.lab_2.data_structures.iterators;

import com.prokopchuk.lab_2.data_structures.nodes.AbstractBinaryTreeNode;

import java.util.Iterator;

public abstract class AbstractBinaryTreeIterator<T, Node extends AbstractBinaryTreeNode<T, Node>> implements Iterator<T> {
    protected Node node;

    AbstractBinaryTreeIterator(Node node) {
        this.node = node;
    }

    private Node getLeftest(Node r) {
        if(r == null) {
            return null;
        }

        while(r.getLeft() != null) {
            r = r.getLeft();
        }
        return r;
    }

    @Override
    public boolean hasNext() {
        return node != null;
    }

    @Override
    public T next() {
        T result = node.getValue();

        if(node.getRight() != null) {
            node = getLeftest(node.getRight());
        } else if (node.getParent() != null && node.getParent().getLeft() == node) {
            node = node.getParent();
        } else {
            while(node.getParent() != null && node == node.getParent().getRight()) {
                node = node.getParent();
            }
        }

        return result;
    }
}
