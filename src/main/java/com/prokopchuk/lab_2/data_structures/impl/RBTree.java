package com.prokopchuk.lab_2.data_structures.impl;

import com.prokopchuk.lab_2.data_structures.builders.impl.RBTreeNodeBuilder;
import com.prokopchuk.lab_2.data_structures.iterators.RBTreeIterator;
import com.prokopchuk.lab_2.data_structures.nodes.RBTreeNodeColor;
import com.prokopchuk.lab_2.data_structures.nodes.RBTreeNode;

import java.util.Iterator;

public class RBTree<T extends Comparable<T>> extends AbstractBinaryTree<T, RBTreeNode<T>> {
    public RBTree() {
        nilNode = new RBTreeNode<T>();
        nilNode.setColor(RBTreeNodeColor.BLACK);
        root = nilNode;
    }

    private void insertFixup(RBTreeNode<T> z) {
        RBTreeNode<T> y;
        while(z.getParent().getColor() == RBTreeNodeColor.RED) {
            if(z.getParent() == z.getParent().getParent().getLeft()) {
                y = z.getParent().getParent().getRight();
                if(y.getColor() == RBTreeNodeColor.RED) {
                    z.getParent().setColor(RBTreeNodeColor.BLACK);
                    y.setColor(RBTreeNodeColor.BLACK);
                    z.getParent().getParent().setColor(RBTreeNodeColor.RED);
                    z = z.getParent().getParent();
                } else {
                    if(z == z.getParent().getRight()) {
                        z = z.getParent();
                        rotateLeft(z);
                    }

                    z.getParent().setColor(RBTreeNodeColor.BLACK);
                    z.getParent().getParent().setColor(RBTreeNodeColor.RED);
                    rotateRight(z.getParent().getParent());
                }
            } else {
                y = z.getParent().getParent().getLeft();
                if(y.getColor() == RBTreeNodeColor.RED) {
                    z.getParent().setColor(RBTreeNodeColor.BLACK);
                    y.setColor(RBTreeNodeColor.BLACK);
                    z.getParent().getParent().setColor(RBTreeNodeColor.RED);
                    z = z.getParent().getParent();
                } else {
                    if(z == z.getParent().getLeft()) {
                        z = z.getParent();
                        rotateRight(z);
                    }

                    z.getParent().setColor(RBTreeNodeColor.BLACK);
                    z.getParent().getParent().setColor(RBTreeNodeColor.RED);
                    rotateLeft(z.getParent().getParent());
                }
            }
        }

        root.setColor(RBTreeNodeColor.BLACK);
    }

    private void deleteFixup(RBTreeNode<T> x) {
        RBTreeNode<T> w;
        while (x != root && x.getColor() == RBTreeNodeColor.BLACK) {
            if (x == x.getParent().getLeft()) {
                w = x.getParent().getRight();

                if (w.getColor() == RBTreeNodeColor.RED) {
                    w.setColor(RBTreeNodeColor.BLACK);
                    x.getParent().setColor(RBTreeNodeColor.RED);
                    rotateLeft(x.getParent());
                    w = x.getParent().getRight();
                }

                if (w.getLeft().getColor() == RBTreeNodeColor.BLACK && w.getRight().getColor() == RBTreeNodeColor.BLACK) {
                    w.setColor(RBTreeNodeColor.RED);
                    x = x.getParent();
                } else {
                    if (w.getRight().getColor() == RBTreeNodeColor.BLACK) {
                        w.getLeft().setColor(RBTreeNodeColor.BLACK);
                        w.setColor(RBTreeNodeColor.RED);
                        rotateRight(w);
                        w = x.getParent().getRight();
                    }

                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(RBTreeNodeColor.BLACK);
                    w.getRight().setColor(RBTreeNodeColor.BLACK);
                    rotateLeft(x.getParent());
                    x = root;
                }
            } else {
                w = x.getParent().getLeft();

                if (w.getColor() == RBTreeNodeColor.RED) {
                    w.setColor(RBTreeNodeColor.BLACK);
                    x.getParent().setColor(RBTreeNodeColor.RED);
                    rotateRight(x.getParent());
                    w = x.getParent().getLeft();
                }

                if (w.getRight().getColor() == RBTreeNodeColor.BLACK && w.getLeft().getColor() == RBTreeNodeColor.BLACK) {
                    w.setColor(RBTreeNodeColor.RED);
                    x = x.getParent();
                } else {
                    if (w.getLeft().getColor() == RBTreeNodeColor.BLACK) {
                        w.getRight().setColor(RBTreeNodeColor.BLACK);
                        w.setColor(RBTreeNodeColor.RED);
                        rotateLeft(w);
                        w = x.getParent().getLeft();
                    }

                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(RBTreeNodeColor.BLACK);
                    w.getLeft().setColor(RBTreeNodeColor.BLACK);
                    rotateRight(x.getParent());
                    x = root;
                }
            }
        }
        x.setColor(RBTreeNodeColor.BLACK);
    }
    @Override
    public void insert(T value) {
        RBTreeNode<T> toInsert = new RBTreeNodeBuilder<T>().setValue(value).setColor(RBTreeNodeColor.RED).setLeft(nilNode).setRight(nilNode).build();
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
        RBTreeNodeColor yOriginalColor = y.getColor();

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

        if(yOriginalColor == RBTreeNodeColor.BLACK) {
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
