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

    private void insertFixup(RBTreeNode<T> z) {
        RBTreeNode<T> y;
        while(z.getParent().getColor() == Color.RED) {
            if(z.getParent() == z.getParent().getParent().getLeft()) {
                y = z.getParent().getParent().getRight();
                if(y.getColor() == Color.RED) {
                    z.getParent().setColor(Color.BLACK);
                    y.setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    z = z.getParent().getParent();
                } else {
                    if(z == z.getParent().getRight()) {
                        z = z.getParent();
                        rotateLeft(z);
                    }

                    z.getParent().setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    rotateRight(z.getParent().getParent());
                }
            } else {
                y = z.getParent().getParent().getLeft();
                if(y.getColor() == Color.RED) {
                    z.getParent().setColor(Color.BLACK);
                    y.setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    z = z.getParent().getParent();
                } else {
                    if(z == z.getParent().getLeft()) {
                        z = z.getParent();
                        rotateRight(z);
                    }

                    z.getParent().setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    rotateLeft(z.getParent().getParent());
                }
            }
        }

        root.setColor(Color.BLACK);
    }

    private void deleteFixup(RBTreeNode<T> x) {
        RBTreeNode<T> w;
        while (x != root && x.getColor() == Color.BLACK) {
            if (x == x.getParent().getLeft()) {
                w = x.getParent().getRight();

                if (w.getColor() == Color.RED) {
                    w.setColor(Color.BLACK);
                    x.getParent().setColor(Color.RED);
                    rotateLeft(x.getParent());
                    w = x.getParent().getRight();
                }

                if (w.getLeft().getColor() == Color.BLACK && w.getRight().getColor() == Color.BLACK) {
                    w.setColor(Color.RED);
                    x = x.getParent();
                } else {
                    if (w.getRight().getColor() == Color.BLACK) {
                        w.getLeft().setColor(Color.BLACK);
                        w.setColor(Color.RED);
                        rotateRight(w);
                        w = x.getParent().getRight();
                    }

                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(Color.BLACK);
                    w.getRight().setColor(Color.BLACK);
                    rotateLeft(x.getParent());
                    x = root;
                }
            } else {
                w = x.getParent().getLeft();

                if (w.getColor() == Color.RED) {
                    w.setColor(Color.BLACK);
                    x.getParent().setColor(Color.RED);
                    rotateRight(x.getParent());
                    w = x.getParent().getLeft();
                }

                if (w.getRight().getColor() == Color.BLACK && w.getLeft().getColor() == Color.BLACK) {
                    w.setColor(Color.RED);
                    x = x.getParent();
                } else {
                    if (w.getLeft().getColor() == Color.BLACK) {
                        w.getRight().setColor(Color.BLACK);
                        w.setColor(Color.RED);
                        rotateLeft(w);
                        w = x.getParent().getLeft();
                    }

                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(Color.BLACK);
                    w.getLeft().setColor(Color.BLACK);
                    rotateRight(x.getParent());
                    x = root;
                }
            }
        }
        x.setColor(Color.BLACK);
    }
    @Override
    public void insert(T value) {
        RBTreeNode<T> toInsert = new RBTreeNodeBuilder<T>().setValue(value).setColor(Color.RED).setLeft(nilNode).setRight(nilNode).build();
        insertNode(toInsert);
        insertFixup(toInsert);
    }

    @Override
    public boolean delete(T value) {
        RBTreeNode<T> toRemove = find(value);

        if(toRemove == nilNode) {
            return false;
        }

        RBTreeNode<T> y = toRemove, x;
        Color yOriginalColor = y.getColor();

        if(toRemove.getLeft() == nilNode) {
            x = toRemove.getRight();
            transplant(toRemove, toRemove.getRight());
        } else if(toRemove.getRight() == nilNode) {
            x = toRemove.getLeft();
            transplant(toRemove, toRemove.getLeft());
        } else {
            y = toRemove.getRight().getLeftest();
            yOriginalColor = y.getColor();
            x = y.getRight();

            if(y.getParent() == toRemove) {
                x.setParent(y);
            } else {
                transplant(y, y.getRight());
                y.setRight(toRemove.getRight());
                y.getRight().setParent(y);
            }

            transplant(toRemove, y);
            y.setLeft(toRemove.getLeft());
            y.getLeft().setParent(y);
            y.setColor(toRemove.getColor());
        }

        if(yOriginalColor == Color.BLACK) {
            deleteFixup(x);
        }

        --length;
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new RBTreeIterator<T>(root.getLeftest());
    }
}
