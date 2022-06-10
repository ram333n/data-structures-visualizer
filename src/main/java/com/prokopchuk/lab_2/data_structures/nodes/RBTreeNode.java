package com.prokopchuk.lab_2.data_structures.nodes;

public class RBTreeNode<T> extends AbstractBinaryTreeNode<T, RBTreeNode<T>> {
    private Color color;

    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
}
