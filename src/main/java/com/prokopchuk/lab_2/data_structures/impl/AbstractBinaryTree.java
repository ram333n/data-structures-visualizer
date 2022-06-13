package com.prokopchuk.lab_2.data_structures.impl;

import com.prokopchuk.lab_2.data_structures.nodes.AbstractBinaryTreeNode;

import java.util.LinkedList;

public abstract class AbstractBinaryTree<T extends Comparable<T>, Node extends AbstractBinaryTreeNode<T, Node>> implements DataStructure<T> {
    protected Node root;
    protected Node nilNode;

    protected Node successor(Node node) {
        if(node.getRight() != nilNode) {
            return node.getRight().getLeftest();
        }

        Node current = node.getParent();
        while(current != nilNode && node == current.getRight()) {
            node = current;
            current = current.getParent();
        }

        return current;
    }

    protected Node find(T value) {
        Node current = root;
        while(current != nilNode && current.getValue().compareTo(value) != 0) {
            current = value.compareTo(current.getValue()) < 0 ? current.getLeft() : current.getRight();
        }
        return current;
    }

    protected void insertNode(Node toInsert) {
        Node pos = nilNode, current = root;
        while(current != nilNode) {
            pos = current;
            if(toInsert.getValue().compareTo(pos.getValue()) < 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        toInsert.setParent(pos);

        if(pos == nilNode) {
            root = toInsert;
        } else if(toInsert.getValue().compareTo(pos.getValue()) < 0) {
            pos.setLeft(toInsert);
        } else {
            pos.setRight(toInsert);
        }
    }

    protected void transplant(Node first, Node second) {
        if(first.getParent() == nilNode) {
            root = second;
        } else if (first == first.getParent().getLeft()) {
            first.getParent().setLeft(second);
        } else {
            first.getParent().setRight(second);
        }

        second.setParent(first.getParent());
    }

    protected void rotateLeft(Node toRotate) {
        Node temp = toRotate.getRight();
        toRotate.setRight(temp.getLeft());

        if(temp.getLeft() != nilNode) {
            temp.getLeft().setParent(toRotate);
        }

        temp.setParent(toRotate.getParent());

        if(toRotate.getParent() == nilNode) {
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

        if(temp.getRight() != nilNode) {
            temp.getRight().setParent(toRotate);
        }

        temp.setParent(toRotate.getParent());

        if(toRotate.getParent() == nilNode) {
            root = temp;
        } else if(toRotate == toRotate.getParent().getRight()) {
            toRotate.getParent().setRight(temp);
        } else {
            toRotate.getParent().setLeft(temp);
        }

        temp.setRight(toRotate);
        toRotate.setParent(temp);
    }

    @Override
    public boolean search(T value) {
        return find(value) != nilNode;
    }

    public void printByLevels() {
        if(root == nilNode) {
            return;
        }

        LinkedList<Node> queue = new LinkedList<Node>();
        queue.add(root);

        while(!queue.isEmpty()) {
            Node current = queue.pop();
            System.out.print(current.getValue() + " ");

            if(current.getLeft() != nilNode) {
                queue.add(current.getLeft());
            }

            if(current.getRight() != nilNode) {
                queue.add(current.getRight());
            }
        }
    }

    public Node getRoot() {
        return root;
    }
}
