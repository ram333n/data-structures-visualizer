package com.prokopchuk.lab_2.data_structures.nodes;

public class BinaryTreeNode<T> extends BaseNode<T> {
    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    public BinaryTreeNode<T> getParent() {
        return parent;
    }

    public void setParent(BinaryTreeNode<T> parent) {
        this.parent = parent;
    }

    protected BinaryTreeNode<T> left;
    protected BinaryTreeNode<T> right;
    protected BinaryTreeNode<T> parent;
}
