package com.prokopchuk.lab_2.data_structures.impl;

import com.prokopchuk.lab_2.data_structures.builders.impl.RBTreeNodeBuilder;
import com.prokopchuk.lab_2.data_structures.iterators.RBTreeIterator;
import com.prokopchuk.lab_2.data_structures.nodes.Color;
import com.prokopchuk.lab_2.data_structures.nodes.RBTreeNode;

import java.util.Iterator;

public class RBTree<T extends Comparable<T>> extends AbstractBinaryTree<T, RBTreeNode<T>> {
    public RBTree() {
        nilNode = new RBTreeNode<T>();
        nilNode.setColor(Color.BLACK);
        root = nilNode;
    }

    private void insertFixup(RBTreeNode<T> toInsert) {
        RBTreeNode<T> y;
        while(toInsert.getParent().getColor() == Color.RED) {
            if(toInsert.getParent() == toInsert.getParent().getParent().getLeft()) {
                y = toInsert.getParent().getParent().getRight();
                if(y.getColor() == Color.RED) {
                    toInsert.getParent().setColor(Color.BLACK);
                    y.setColor(Color.BLACK);
                    toInsert.getParent().getParent().setColor(Color.RED);
                    toInsert = toInsert.getParent().getParent();
                } else {
                    if(toInsert == toInsert.getParent().getRight()) {
                        toInsert = toInsert.getParent();
                        rotateLeft(toInsert);
                    }

                    toInsert.getParent().setColor(Color.BLACK);
                    toInsert.getParent().getParent().setColor(Color.RED);
                    rotateRight(toInsert.getParent().getParent());
                }
            } else {
                y = toInsert.getParent().getParent().getLeft();
                if(y.getColor() == Color.RED) {
                    toInsert.getParent().setColor(Color.BLACK);
                    y.setColor(Color.BLACK);
                    toInsert.getParent().getParent().setColor(Color.RED);
                    toInsert = toInsert.getParent().getParent();
                } else {
                    if(toInsert == toInsert.getParent().getLeft()) {
                        toInsert = toInsert.getParent();
                        rotateRight(toInsert);
                    }

                    toInsert.getParent().setColor(Color.BLACK);
                    toInsert.getParent().getParent().setColor(Color.RED);
                    rotateLeft(toInsert.getParent().getParent());
                }
            }
        }

        root.setColor(Color.BLACK);
    }
    @Override
    public void insert(T value) {
        RBTreeNode<T> toInsert = new RBTreeNodeBuilder<T>().setValue(value).setColor(Color.RED).setLeft(nilNode).setRight(nilNode).build();
        insertNode(toInsert);
        insertFixup(toInsert);
    }

    @Override
    public boolean delete(T value) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new RBTreeIterator<T>(root.getLeftest());
    }
}
