package com.prokopchuk.lab_2.data_structures.impl;

import com.prokopchuk.lab_2.data_structures.builders.impl.BSTreeNodeBuilder;
import com.prokopchuk.lab_2.data_structures.nodes.BSTreeNode;

import java.util.Iterator;

public class BSTree<T extends Comparable<T>> implements DataStructure<T> {
    private BSTreeNode<T> root;
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
    public boolean search(T value) {
        BSTreeNode<T> current = root;
        while(current != null && current.getValue().compareTo(value) != 0) {
            current = value.compareTo(current.getValue()) < 0 ? current.getLeft() : current.getRight();
        }
        return current != null;
    }

    @Override
    public boolean delete(T value) {
        //TODO : successor, predecessor, find(T value) : BSTreeNode(mb create AbstractBinaryTree)
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
