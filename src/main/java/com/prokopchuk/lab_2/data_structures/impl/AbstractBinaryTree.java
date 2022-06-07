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

    protected void transplant(Node first, Node second) {
        if(first.getParent() == null) {
            root = second;
        } else if (first == first.getParent().getLeft()) {
            first.getParent().setLeft(second);
        } else {
            first.getParent().setRight(second);
        }

        if(second != null) {
            second.setParent(first.getParent());
        }
    }

    protected void rotateLeft(Node toRotate) {
        Node temp = toRotate.getRight();
        toRotate.setRight(temp.getLeft());

        if(temp.getLeft() != null) {
            temp.getLeft().setParent(toRotate);
        }

        temp.setParent(toRotate.getParent());

        if(toRotate.getParent() == null) {
            root = temp;
        } else if(toRotate == toRotate.getParent().getLeft()) {
            toRotate.getParent().setLeft(temp);
        } else {
            toRotate.getParent().setRight(temp);
        }

        temp.setLeft(toRotate);
        toRotate.setParent(temp);
    }

    protected void rotateRight(Node toRotate) {
        Node temp = toRotate.getLeft();
        toRotate.setLeft(temp.getRight());

        if(temp.getRight() != null) {
            temp.getRight().setParent(toRotate);
        }

        temp.setParent(toRotate.getParent());

        if(toRotate.getParent() == null) {
            root = temp;
        } else if(toRotate == toRotate.getParent().getLeft()) {
            toRotate.getParent().setRight(temp);
        } else {
            toRotate.getParent().setLeft(temp);
        }

        temp.setRight(toRotate);
        toRotate.setParent(temp);
    }

    @Override
    public boolean search(T value) {
        return find(value) != null;
    }
}
