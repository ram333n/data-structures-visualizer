package com.prokopchuk.lab_2.data_structures.nodes;

public abstract class AbstractBinaryTreeNode<T, Node extends BaseNode<T>> extends BaseNode<T>  {
    protected Node left;
    protected Node right;
    protected Node parent;

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}
