package com.prokopchuk.lab_2.data_structures.nodes;

public class RBTreeNode<T> extends AbstractBinaryTreeNode<T, RBTreeNode<T>> {
    private RBTreeNodeColor color;

    public RBTreeNodeColor getColor() {
        return color;
    }
    public void setColor(RBTreeNodeColor color) {
        this.color = color;
    }
}
