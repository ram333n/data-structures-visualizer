package com.prokopchuk.lab_2.data_structures.impl;

import com.prokopchuk.lab_2.data_structures.builders.impl.BSTreeNodeBuilder;
import com.prokopchuk.lab_2.data_structures.iterators.BSTreeIterator;
import com.prokopchuk.lab_2.data_structures.nodes.BSTreeNode;

import java.util.Iterator;

public class BSTree<T extends Comparable<T>> extends AbstractBinaryTree<T, BSTreeNode<T>> {
    @Override
    public void insert(T value) {
        BSTreeNode<T> toInsert = new BSTreeNodeBuilder<T>().setValue(value).build();
        BSTreeNode<T> pos = null, current = root;
        while(current != null) {
            pos = current;
            if(toInsert.getValue().compareTo(pos.getValue()) < 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        toInsert.setParent(pos);

        if(pos == null) {
            root = toInsert;
        } else {
            if(toInsert.getValue().compareTo(pos.getValue()) < 0) {
                pos.setLeft(toInsert);
            } else {
                pos.setRight(toInsert);
            }
        }
    }

    @Override
    public boolean delete(T value) {
        BSTreeNode<T> toRemove = find(value);

        if(toRemove == null) {
            return false;
        }

        BSTreeNode<T> successor, temp;

        if(toRemove.getLeft() == null || toRemove.getRight() == null) {
            successor = toRemove;
        } else {
            successor = successor(toRemove);
        }

        if(successor.getLeft() != null) {
            temp = successor.getLeft();
        } else {
            temp = successor.getRight();
        }

        //TODO : implement delete;

        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new BSTreeIterator<T>(root.getLeftest());
    }
}
