package com.prokopchuk.lab_2.data_structures.impl;

import com.prokopchuk.lab_2.data_structures.builders.impl.BSTreeNodeBuilder;
import com.prokopchuk.lab_2.data_structures.iterators.BSTreeIterator;
import com.prokopchuk.lab_2.data_structures.nodes.BSTreeNode;

import java.util.Iterator;

public class BSTree<T extends Comparable<T>> extends AbstractBinaryTree<T, BSTreeNode<T>> {
    public BSTree() {
        nilNode = new BSTreeNode<T>();
        root = nilNode;
    }
    @Override
    public void insert(T value) {
        BSTreeNode<T> toInsert = new BSTreeNodeBuilder<T>().setValue(value).setLeft(nilNode).setRight(nilNode).build();
        insertNode(toInsert);
    }

    @Override
    public boolean delete(T value) {
        BSTreeNode<T> toRemove = find(value);

        if(toRemove == nilNode) {
            return false;
        }

        if(toRemove.getLeft() == nilNode) {
            transplant(toRemove, toRemove.getRight());
        } else if (toRemove.getRight() == nilNode) {
            transplant(toRemove, toRemove.getLeft());
        } else {
            BSTreeNode<T> y = toRemove.getRight().getLeftest();
            if(y.getParent() != toRemove) {
                transplant(y, y.getRight());
                y.setRight(toRemove.getRight());
                y.getRight().setParent(y);
            }
            transplant(toRemove, y);
            y.setLeft(toRemove.getLeft());
            y.getLeft().setParent(y);
        }

        --length;
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new BSTreeIterator<T>(root.getLeftest());
    }
}
