package com.prokopchuk.lab_2.data_structures.impl;

import com.prokopchuk.lab_2.data_structures.nodes.AbstractBinaryTreeNode;

public abstract class AbstractBinaryTree<T extends Comparable<T>, Node extends AbstractBinaryTreeNode<T, Node>> implements DataStructure<T> {
    protected Node root;

    protected Node successor(Node node) {
        if(node.getRight() != null) {
            return node.getRight().getLeftest();
        }

        Node current = node.getParent();
        while(current != null && node == current.getRight()) {
            node = current;
            current = current.getParent();
        }

        return current;
    }

    protected Node find(T value) {
        Node current = root;
        while(current != null && current.getValue().compareTo(value) != 0) {
            current = value.compareTo(current.getValue()) < 0 ? current.getLeft() : current.getRight();
        }
        return current;
    }

    @Override
    public boolean search(T value) {
        return find(value) != null;
    }
}
